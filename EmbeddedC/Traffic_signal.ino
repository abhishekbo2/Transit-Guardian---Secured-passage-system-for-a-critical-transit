#include <SoftwareSerial.h>

// Pins for TF1
const int TF1_RED = 2;
const int TF1_YELLOW = 3;
const int TF1_GREEN = 4;

// Pins for TF2
const int TF2_RED = 5;
const int TF2_YELLOW = 6;
const int TF2_GREEN = 7;

// Pins for TF3
const int TF3_RED = 8;
const int TF3_YELLOW = 9;
const int TF3_GREEN = 10;

// Bluetooth communication setup
SoftwareSerial bluetooth(11, 12);  // RX, TX pins for Bluetooth communication
char receivedCommand = '\0';       // Variable to store received Bluetooth command

// Timing constants (in milliseconds)
const unsigned long RED_DURATION = 6000;    // 6 seconds
const unsigned long YELLOW_DURATION = 3000; // 3 seconds
const unsigned long GREEN_DURATION = 6000;  // 6 seconds

// State variables for each signal
enum SignalState { RED, YELLOW, GREEN };

// Current active signal and its state
int activeSignal = 1;  // Start with signal 1
SignalState activeState = RED;

unsigned long lastChangeTime = 0;
bool isInterrupted = false;  // Flag to handle interruptions
int yesPressCount = 0;       // Track the count of "YES" button presses

// Variables for yellow-to-green transition
bool transitioningToGreen = false; // Flag to manage YELLOW-to-GREEN transition
unsigned long yellowStartTime = 0; // Start time of YELLOW state during interruptions

void setup() {
    // Initialize pin modes
    pinMode(TF1_RED, OUTPUT);
    pinMode(TF1_YELLOW, OUTPUT);
    pinMode(TF1_GREEN, OUTPUT);

    pinMode(TF2_RED, OUTPUT);
    pinMode(TF2_YELLOW, OUTPUT);
    pinMode(TF2_GREEN, OUTPUT);

    pinMode(TF3_RED, OUTPUT);
    pinMode(TF3_YELLOW, OUTPUT);
    pinMode(TF3_GREEN, OUTPUT);

    // Set all signals to RED initially
    setSignal(1, true, false, false);
    setSignal(2, true, false, false);
    setSignal(3, true, false, false);

    // Initialize the first signal
    lastChangeTime = millis();
    bluetooth.begin(9600);  // Initialize Bluetooth
    Serial.begin(9600);     // For debugging
}

void loop() {
    // Check for Bluetooth command
    if (bluetooth.available()) {
        receivedCommand = bluetooth.read();
        handleBluetoothCommand(receivedCommand);
    }

    if (isInterrupted) {
        // Handle interruption logic
        handleInterruptedSignal();
    } else {
        // Normal operation: Update the active signal
        updateActiveSignal();
    }
}

void updateActiveSignal() {
    unsigned long currentTime = millis();

    switch (activeState) {
        case RED:
            if (currentTime - lastChangeTime >= RED_DURATION) {
                activeState = YELLOW;
                setActiveSignalState(YELLOW);  // Transition to YELLOW
                lastChangeTime = currentTime;
            }
            break;

        case YELLOW:
            if (currentTime - lastChangeTime >= YELLOW_DURATION) {
                activeState = GREEN;
                setActiveSignalState(GREEN);  // Transition to GREEN
                lastChangeTime = currentTime;
            }
            break;

        case GREEN:
            if (currentTime - lastChangeTime >= GREEN_DURATION) {
                activeState = RED;
                setActiveSignalState(RED);    // Transition back to RED
                // Move to the next signal
                activeSignal = (activeSignal % 3) + 1;
                lastChangeTime = currentTime;
            }
            break;
    }
}

void setActiveSignalState(SignalState state) {
    // Set all signals to RED by default
    setSignal(1, true, false, false);
    setSignal(2, true, false, false);
    setSignal(3, true, false, false);

    // Update only the active signal to the specified state
    switch (state) {
        case RED:
            setSignal(activeSignal, true, false, false);
            break;

        case YELLOW:
            setSignal(activeSignal, false, true, false);
            break;

        case GREEN:
            setSignal(activeSignal, false, false, true);
            break;
    }
}

void setSignal(int signal, bool red, bool yellow, bool green) {
    // Update pins based on signal number
    switch (signal) {
        case 1:
            digitalWrite(TF1_RED, red ? HIGH : LOW);
            digitalWrite(TF1_YELLOW, yellow ? HIGH : LOW);
            digitalWrite(TF1_GREEN, green ? HIGH : LOW);
            break;

        case 2:
            digitalWrite(TF2_RED, red ? HIGH : LOW);
            digitalWrite(TF2_YELLOW, yellow ? HIGH : LOW);
            digitalWrite(TF2_GREEN, green ? HIGH : LOW);
            break;

        case 3:
            digitalWrite(TF3_RED, red ? HIGH : LOW);
            digitalWrite(TF3_YELLOW, yellow ? HIGH : LOW);
            digitalWrite(TF3_GREEN, green ? HIGH : LOW);
            break;
    }
}

void handleBluetoothCommand(char command) {
    if (command == 'Y' || command == 'y') {
        Serial.println("Received 'Yes' command.");
        isInterrupted = true;
        yesPressCount++;
        transitioningToGreen = false; // Reset transition flag on new interruption
    }
}

void handleInterruptedSignal() {
    unsigned long currentTime = millis();

    if (yesPressCount == 1) {
        if (!transitioningToGreen) {
            // Transition TF2 to YELLOW
            setSignal(1, true, false, false);  // TF1 RED
            setSignal(2, false, true, false); // TF2 YELLOW
            setSignal(3, true, false, false); // TF3 RED
            yellowStartTime = currentTime;    // Start YELLOW timer
            transitioningToGreen = true;      // Mark transition as started
        } else if (currentTime - yellowStartTime >= YELLOW_DURATION) {
            // After 2 seconds, transition to GREEN
            setSignal(1, true, false, false);  // TF1 RED
            setSignal(2, false, false, true); // TF2 GREEN
            setSignal(3, true, false, false); // TF3 RED
        }
    } else if (yesPressCount == 2) {
        if (!transitioningToGreen) {
            // Transition TF3 to YELLOW
            setSignal(1, true, false, false);  // TF1 RED
            setSignal(2, true, false, false);  // TF2 RED
            setSignal(3, false, true, false); // TF3 YELLOW
            yellowStartTime = currentTime;    // Start YELLOW timer
            transitioningToGreen = true;      // Mark transition as started
        } else if (currentTime - yellowStartTime >= YELLOW_DURATION) {
            // After 2 seconds, transition to GREEN
            setSignal(1, true, false, false);  // TF1 RED
            setSignal(2, true, false, false);  // TF2 RED
            setSignal(3, false, false, true); // TF3 GREEN
        }
    } else if (yesPressCount == 3) {
        // After 3rd "YES", return to normal operation
        Serial.println("Returning to normal operation.");
        isInterrupted = false;            // Exit interrupted state
        yesPressCount = 0;                // Reset the count
        transitioningToGreen = false;     // Reset transition flag
        activeState = RED;                // Resume normal operation
        activeSignal = 1;                 // Start with the first signal
        lastChangeTime = millis();        // Reset the timer
    }
} 

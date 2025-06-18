package com.aibhi.we_will;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;
import java.io.IOException;


public class profile_page extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private ImageView profileImage, editProfileImageIcon;
    private TextView profileName, profileDetails;
    private ImageView homeButton, callButton, profileButton;
    private TextView editprofileOption, notificationOption, languageOption, vehicleDetailsOption, themeOption, helpSupportOption, contactUsOption, privacyPolicyOption;
    private Intent data;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize views
        profileImage = findViewById(R.id.profileImage);
        editProfileImageIcon = findViewById(R.id.editProfileImageIcon);
        profileName = findViewById(R.id.profileName);
        profileDetails = findViewById(R.id.profileDetails);
        editprofileOption = findViewById(R.id.editprofileOption);
        notificationOption = findViewById(R.id.notificationOption);
        languageOption = findViewById(R.id.languageOption);
        vehicleDetailsOption = findViewById(R.id.vehicleDetailsOption);
        themeOption = findViewById(R.id.themeOption);
        helpSupportOption = findViewById(R.id.helpSupportOption);
        contactUsOption = findViewById(R.id.contactUsOption);
        privacyPolicyOption = findViewById(R.id.privacyPolicyOption);
        homeButton = findViewById(R.id.home_button);
        callButton = findViewById(R.id.call_button);
        profileButton = findViewById(R.id.profile_button);
        // Set up listeners

        // Home Button Functionality
        homeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(profile_page.this, home_page.class); // Replace 'profile_page' with your actual profile activity
            startActivity(homeIntent);
        });

        // Call Button Functionality
        callButton.setOnClickListener(v -> {
            // Intent to make a call
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:8296377884")); // Replace with actual number
            startActivity(callIntent);
        });

        // Profile Button Functionality
        profileButton.setOnClickListener(v -> {
            Toast.makeText(profile_page.this, "You are already on the Profile Page.", Toast.LENGTH_SHORT).show();
        });

        // Handle profile image click to enlarge
        profileImage.setOnClickListener(view -> {
            if (selectedImageUri != null) {
                Intent intent = new Intent(profile_page.this, imagepreview.class);
                intent.putExtra("imageUri", selectedImageUri.toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "No profile image to display!", Toast.LENGTH_SHORT).show();
            }
        });


        // Handle edit profile image click
        editProfileImageIcon.setOnClickListener(view -> {
            showImagePickerDialog();
        });


        editprofileOption.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, profileedit.class);
            startActivity(intent);
        });

        notificationOption.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, notification.class);
            startActivity(intent);
        });

        languageOption.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, languages.class);
            startActivity(intent);
        });

        vehicleDetailsOption.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, vehicledetails.class);
            startActivity(intent);
        });

        themeOption.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, theamspro.class);
            startActivity(intent);
        });

        privacyPolicyOption.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, privacypolicy.class);
            startActivity(intent);
        });

        // No navigation needed for Help & Support and Contact Us
    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Profile Image")
                .setItems(new String[]{"Select from Gallery", "Cancel"}, (dialog, which) -> {
                    if (which == 0) {
                        openImagePicker();
                    } else {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                // Set the selected image as profile image
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                profileImage.setImageBitmap(bitmap);
                Toast.makeText(this, "Profile image updated successfully!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to update image!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}

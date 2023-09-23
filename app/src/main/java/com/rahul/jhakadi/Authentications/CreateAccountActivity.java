package com.rahul.jhakadi.Authentications;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rahul.jhakadi.Model.UserModel;
import com.rahul.jhakadi.Util;
import com.rahul.jhakadi.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private ActivityCreateAccountBinding binding;
    int PRegCode = 1;
    int REQUESCODE = 1;

    Uri pickedImaUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        binding.signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.userNameEdit.getText().toString();
                String email = binding.emailEdit.getText().toString();
                String password = binding.passwordNameEdit.getText().toString();
                String password2 = binding.repasswordNameEdit.getText().toString();
                //String phonetext = binding.phoneNumberEdit.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    ///we need to display a error message
                    new Util().showMessage(CreateAccountActivity.this, "Please fill all fields");
                } else if (!password.equals(password2)) {
                    new Util().showMessage(CreateAccountActivity.this, "Password are not matching");
                } else if (password.length() <6 ) {
                    new Util().showMessage(CreateAccountActivity.this,"Password lengthmust be greater then 6 letter");
                } else {
                    CreateUserAccount(email, name, password);
                }
            }
        });

        binding.userProfilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestedForPermission();
                } else {
                    openGallery();
                }

            }
        });
    }

    /**
     * Create user account
     * @param email
     * @param name
     * @param password
     */
    private void CreateUserAccount(String email, final String name, String password) {
        //this method create user account with specific email password
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //after creating account now we update user information like name picture
                            //updateUserInfo(name, pickedImaUri, firebaseAuth.getCurrentUser());
                            UserModel userModel = new UserModel(name, email, password);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);
                            //user account create succesful
                            new Util().showMessage(CreateAccountActivity.this, "Account created Successful");
                            Intent mainactivity= new Intent(CreateAccountActivity.this, SignInActivity.class);
                            startActivity(mainactivity);
                            finish();
                        } else {
                            //account creation failed
                            new Util().showMessage(CreateAccountActivity.this, "Account creation failed" + task.getException());
                        }
                    }
                });
    }

    private void checkAndRequestedForPermission() {
        if (ContextCompat.checkSelfPermission(CreateAccountActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreateAccountActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new Util().showMessage(CreateAccountActivity.this, "Please accept for reuired permission");
            } else {
                ActivityCompat.requestPermissions(CreateAccountActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PRegCode);
            }
        } else {
            openGallery();
        }
    }

    /**
     * the user has successfully picked an image
     * we need to save its reference to a Uri variable
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            pickedImaUri = data.getData();
            binding.userProfilepic.setImageURI(pickedImaUri);
        }
    }

    /**
     * open the gallery to pick the image
     */
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);

    }

    /**
     *  update user photo and name
     * @param username
     * @param pickedImaUri
     * @param currentUser
     */
    private void updateUserInfo(final String username, Uri pickedImaUri, final FirebaseUser currentUser) {

        //first we need to upload user photo to firebase storage and get url

        StorageReference mstorage = FirebaseStorage.getInstance().getReference().child("user_photos");
        StorageReference imgFilePath = mstorage.child(pickedImaUri.getLastPathSegment());
        imgFilePath.putFile(pickedImaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //image uploaded successfully
                // now get over image url

                imgFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //uri contain image url

                        UserProfileChangeRequest profleupdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profleupdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            //user information update successfully
                                            new Util().showMessage(CreateAccountActivity.this, "Register Complete");
                                            // upadedUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }

}


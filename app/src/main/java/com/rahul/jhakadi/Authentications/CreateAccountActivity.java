package com.rahul.jhakadi.Authentications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rahul.jhakadi.R;
import com.rahul.jhakadi.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ActivityCreateAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        binding.signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.userNameEdit.getText().toString();
                String email = binding.emailEdit.getText().toString();
                String password = binding.passwordNameEdit.getText().toString();
                String password2 = binding.repasswordNameEdit.getText().toString();
                String phonetext = binding.phoneNumberEdit.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phonetext.isEmpty()) {
                    ///we need to display a error message
                    showMessage("Please fill all fields");
                    binding.signupbutton.setVisibility(View.VISIBLE);
                } else if (!password.equals(password2)) {
                    showMessage("Password are not matching");
                } else {
                    CreateUserAccount(email, name, password);
                }
            }
        });
    }

    private void CreateUserAccount(String email, final String name, String password) {
        //this method create user account with specific email password
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user account create succesful
                            showMessage("Account created");
                            //after creating account now we update user information like name picture
                            //updateUserInfo(name ,pickedImaUri,firebaseAuth.getCurrentUser());
                        } else {
                            //account creation failed
                            showMessage("Account creation failed" + task.getException());
                            binding.signupbutton.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

    /// update user photo and name

/*
    private void updateUserInfo(final String username, Uri pickedImaUri,final FirebaseUser currentUser) {

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
                                        if (task.isSuccessful()){
                                            //user information update successfully
                                            showMessage("Register Complete");
                                            // upadedUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });


    }*/

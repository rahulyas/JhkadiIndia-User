package com.rahul.jhakadi.Authentications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rahul.jhakadi.MainActivity;
import com.rahul.jhakadi.R;
import com.rahul.jhakadi.Util;
import com.rahul.jhakadi.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainactivity= new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(mainactivity);
                finish();
            }
        });

        binding.signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail= binding.userNameEdit.getText().toString();
                final String Password= binding.passwordNameEdit.getText().toString();

                if(mail.isEmpty() || Password.isEmpty()){
                    new Util().showMessage(SignInActivity.this,"Please Verify All Field");
                }
                else
                {
                    signIn(mail,Password);

                }
            }
        });

    }

    private void signIn(String mail, String Password) {
        mAuth.signInWithEmailAndPassword(mail,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent intent=new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    new Util().showMessage(SignInActivity.this,task.getException().getMessage());
                }

            }
        });
    }
}
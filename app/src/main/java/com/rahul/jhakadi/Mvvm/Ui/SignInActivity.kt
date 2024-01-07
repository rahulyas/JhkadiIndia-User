package com.rahul.jhakadi.Mvvm.Ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahul.jhakadi.MainActivity
import com.rahul.jhakadi.Mvvm.Authentication.AuthRepository
import com.rahul.jhakadi.Mvvm.Authentication.AuthViewModel
import com.rahul.jhakadi.Mvvm.Authentication.AuthViewModelFactory
import com.rahul.jhakadi.Util
import com.rahul.jhakadi.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val repository = AuthRepository()
        val factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        clickListener()
    }

    fun clickListener(){

        viewModel.signInStatus.observe(this, Observer { isSuccessful ->
            if (isSuccessful) {
                Util().showMessage(this,"Sign-IN successful!")
                // Navigate to the next screen or perform other actions as needed
                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle sign-up failure if needed
                Util().showMessage(this,"Sign-up failure!")
            }
        })

        binding!!.signinbutton.setOnClickListener {
            viewModel.signIn(binding!!.userNameEdit.text.toString(), binding!!.passwordNameEdit.text.toString())
        }

        // Set up click listener for forgot password
        binding!!.forgotpassword.setOnClickListener {
            viewModel.resetPassword(binding!!.userNameEdit.text.toString())
        }

        // Observe the reset password status
        viewModel.resetPasswordStatus.observe(this, Observer { isSuccessful ->
            if (isSuccessful) {
                Util().showMessage(this,"Password reset email sent. Check your inbox.")
            } else {
                Util().showMessage(this,"Failed to send password reset email.")
            }
        })

        // Set up click listener for SignUp fragment
        binding!!.signupbutton.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
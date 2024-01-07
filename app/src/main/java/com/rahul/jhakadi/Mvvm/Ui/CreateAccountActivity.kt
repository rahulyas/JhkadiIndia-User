package com.rahul.jhakadi.Mvvm.Ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahul.jhakadi.Mvvm.Authentication.AuthRepository
import com.rahul.jhakadi.Mvvm.Authentication.AuthViewModel
import com.rahul.jhakadi.Mvvm.Authentication.AuthViewModelFactory
import com.rahul.jhakadi.Util
import com.rahul.jhakadi.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private var binding: ActivityCreateAccountBinding? = null
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val repository = AuthRepository()
        val factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        clickListener()
    }

    fun clickListener(){

        binding!!.signupbutton.setOnClickListener {
            viewModel.signUp(binding!!.emailidEdit.text.toString(), binding!!.passwordNameEdit.text.toString())
        }

        // Observe the sign-up status
        viewModel.signUpStatus.observe(this, Observer { isSuccessful ->
            if (isSuccessful) {
                Util().showMessage(this,"Sign-up successful!")
                // Navigate to the next screen or perform other actions as needed
                val intent = Intent(this@CreateAccountActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle sign-up failure if needed
                Util().showMessage(this,"Sign-up failure!")
            }
        })

        // Observe the sign-up error
        viewModel.signUpError.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Util().showMessage(this,it)
            }
        })
    }

}
package com.rahul.jhakadi.Mvvm.Authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    // MutableLiveData to observe the sign-up status
    val signUpStatus = MutableLiveData<Boolean>()
    val signUpError = MutableLiveData<String?>()

    val signInStatus = MutableLiveData<Boolean>()

    val resetPasswordStatus = MutableLiveData<Boolean>()
    val resetPasswordError = MutableLiveData<String?>()

    private val auth = FirebaseAuth.getInstance()

    fun signUp(email: String, password: String) {
        if (!isValidEmail(email)) {
            signUpError.value = "Invalid email address"
            return
        }

        if (!isValidPassword(password)) {
            signUpError.value = "Invalid password. Password must contain at least one uppercase letter, one digit, and be at least 8 characters long."
            return
        }

        repository.signUp(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signUpStatus.value = true
                    signUpError.value = null
                } else {
                    signUpStatus.value = false
                    signUpError.value = "Sign-up failed. Please try again."
                }
            }
    }

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signInStatus.value = task.isSuccessful
            }
    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    private fun isValidEmail(email: String): Boolean {
        // Simple email validation, you might want to use a regex for a more comprehensive check
        return email.endsWith("@gmail.com")
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        return password.matches(passwordRegex.toRegex())
    }

    fun resetPassword(email: String) {
        repository.resetPassword(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resetPasswordStatus.value = true
                    resetPasswordError.value = null
                } else {
                    resetPasswordStatus.value = false
                    resetPasswordError.value = "Password reset failed. Please try again."
                }
            }
    }
}
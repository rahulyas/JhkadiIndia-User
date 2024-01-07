package com.rahul.jhakadi.Mvvm.Authentication

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun signUp(email: String, password: String) = auth.createUserWithEmailAndPassword(email, password)

    fun signIn(email: String, password: String) = auth.signInWithEmailAndPassword(email, password)

    fun signOut() = auth.signOut()

    fun getCurrentUser() = auth.currentUser

    fun resetPassword(email: String) = auth.sendPasswordResetEmail(email)

}
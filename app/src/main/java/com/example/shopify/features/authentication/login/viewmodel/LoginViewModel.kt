package com.example.shopify.features.authentication.login.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModel
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel(private val fragmentActivity: FragmentActivity): ViewModel(){
    private val TAG = "LoginViewModel"
    private var auth: FirebaseAuth

    init {
        auth = Firebase.auth
    }
    fun loginCustomerFirebase(customerEmail: String, customerPassword: String, callback: (Boolean) -> Unit){
        auth.signInWithEmailAndPassword(customerEmail, customerPassword)
            .addOnCompleteListener(fragmentActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
                    callback(true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    callback(false)
                }
            }
    }
}
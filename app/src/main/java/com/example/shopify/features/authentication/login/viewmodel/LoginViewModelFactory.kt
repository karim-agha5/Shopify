package com.example.shopify.features.authentication.login.viewmodel

import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository

class LoginViewModelFactory(private val fragmentActivity: FragmentActivity
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(fragmentActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.shopify.features.authentication.registration.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository

class RegistrationViewModelFactory(private val _repo: IRegistrationRepository, private val fragmentActivity: FragmentActivity
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(_repo , fragmentActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
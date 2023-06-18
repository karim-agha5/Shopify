package com.example.shopify.features.authentication.registration.viewmodel

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository

class RegistrationViewModelFactory(private val _repo: IRegistrationRepository, private val draftOrderRepo: IDraftOrderRepository, private val activity: Activity
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(_repo, draftOrderRepo, activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.shopify.features.orders_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.orders_details.repository.IOrdersDetailsRepository

class OrdersDetailsViewModelFactory (private val repo: IOrdersDetailsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OrdersDetailsViewModel::class.java)) {
            OrdersDetailsViewModel(repo) as T
        } else {
            throw IllegalArgumentException("Orders Details ViewModel class not found")
        }
    }
}
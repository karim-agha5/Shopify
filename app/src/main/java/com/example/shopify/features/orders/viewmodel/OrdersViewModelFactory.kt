package com.example.shopify.features.orders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.orders.repository.IOrdersRepository

class OrdersViewModelFactory(
    private val repo: IOrdersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OrdersViewModel::class.java)) {
            OrdersViewModel(repo) as T
        } else {
            throw IllegalArgumentException("Orders ViewModel class not found")
        }
    }
}
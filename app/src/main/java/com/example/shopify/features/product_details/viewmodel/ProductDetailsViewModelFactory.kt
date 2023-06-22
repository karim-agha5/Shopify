package com.example.shopify.features.product_details.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository

class ProductDetailsViewModelFactory(private val draftOrderId: Long?, private val _repo: IDraftOrderRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailsViewModel(draftOrderId, _repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
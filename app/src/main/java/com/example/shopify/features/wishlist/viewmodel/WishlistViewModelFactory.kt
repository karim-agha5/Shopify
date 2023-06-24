package com.example.shopify.features.wishlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModel

class WishlistViewModelFactory(private val draftOrderId: Long?, private val _repo: IDraftOrderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WishlistViewModel(draftOrderId, _repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

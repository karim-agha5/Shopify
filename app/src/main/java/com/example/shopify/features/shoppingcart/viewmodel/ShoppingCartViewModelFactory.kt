package com.example.shopify.features.shoppingcart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.features.draftorder.data.IShoppingCartRepository

class ShoppingCartViewModelFactory(
    private val shoppingCartRepository: IShoppingCartRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingCartViewModel(shoppingCartRepository) as T
    }
}
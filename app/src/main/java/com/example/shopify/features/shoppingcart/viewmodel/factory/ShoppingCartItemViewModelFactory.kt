package com.example.shopify.features.shoppingcart.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartListItemsViewModel

class ShoppingCartViewModelFactory(
    private val shoppingCartRepository: IDraftOrderRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingCartListItemsViewModel(shoppingCartRepository) as T
    }
}
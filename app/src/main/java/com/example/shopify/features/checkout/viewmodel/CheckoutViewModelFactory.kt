package com.example.shopify.features.checkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.checkout.data.ICheckoutRepository
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartListItemsViewModel

class CheckoutViewModelFactory(
    private val checkoutRepository: ICheckoutRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CheckoutViewModel(checkoutRepository) as T
    }
}
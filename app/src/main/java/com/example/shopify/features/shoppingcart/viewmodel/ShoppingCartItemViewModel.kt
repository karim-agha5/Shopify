package com.example.shopify.features.shoppingcart.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopify.core.common.features.draftorder.data.IShoppingCartRepository

class ShoppingCartItemViewModel(
    private val shoppingCartRepository: IShoppingCartRepository
) : ViewModel() {
}
package com.example.shopify.features.wishlist.view

import com.example.shopify.core.common.data.model.Product

interface IOnFavoriteClickListener {
    fun navigateToDetails(product: Product)
}
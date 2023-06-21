package com.example.shopify.core.common.interfaces

import com.example.shopify.core.common.data.model.Product

interface OnProductClickListener {
    fun navigateToDetailsScreen(currentProduct: Product)
}
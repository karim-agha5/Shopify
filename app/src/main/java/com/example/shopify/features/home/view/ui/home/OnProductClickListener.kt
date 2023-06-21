package com.example.shopify.features.home.view.ui.home

import com.example.shopify.core.common.data.model.Product

interface OnProductClickListener {
    fun navigateToDetailsScreen(currentProduct: Product)
}
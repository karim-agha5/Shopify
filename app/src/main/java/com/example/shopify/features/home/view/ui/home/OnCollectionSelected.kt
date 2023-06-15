package com.example.shopify.features.home.view.ui.home

interface OnCollectionSelected {
    fun navigateToProductsScreen(collectionId: Long, collectionTitle: String )
}
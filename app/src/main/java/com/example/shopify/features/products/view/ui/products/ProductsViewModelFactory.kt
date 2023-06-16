package com.example.shopify.features.products.view.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.products.repository.IProductsRepository

class ProductsViewModelFactory (
    private val repo: IProductsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            ProductsViewModel(repo) as T
        } else {
            throw IllegalArgumentException("HomeViewModel class not found")
        }
    }
}
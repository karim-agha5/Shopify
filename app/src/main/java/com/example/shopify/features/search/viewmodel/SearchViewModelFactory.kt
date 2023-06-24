package com.example.shopify.features.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModel

class SearchViewModelFactory(private val products: List<Product>?): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(products) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
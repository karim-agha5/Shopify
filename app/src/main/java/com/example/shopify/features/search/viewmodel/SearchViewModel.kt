package com.example.shopify.features.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(private val products: List<Product>): ViewModel() {
    private val _filteredProductsLiveData = MutableLiveData<List<Product>>()
    val filteredProductsLiveData: LiveData<List<Product>> = _filteredProductsLiveData

    fun filterProducts(key: String) {
        viewModelScope.launch {
            // Use Kotlin coroutine operators to handle searching
            val filteredProducts = MutableStateFlow(products)

            filteredProducts
                .debounce(300) // Add a debounce delay to handle rapid text changes
                .filter { key.isNotBlank() } // Filter out empty search queries
                .distinctUntilChanged() // Only emit when the search query changes
                .flatMapLatest { products ->
                    // Perform the filtering based on product title, vendor, and product_type
                    filteredProducts.map { productList ->
                        productList.filter { product ->
                            product.title.contains(key, ignoreCase = true) ||
                                    product.vendor.contains(key, ignoreCase = true) ||
                                    product.product_type.contains(key, ignoreCase = true)
                        }
                    }
                }
                .flowOn(Dispatchers.Default) // Perform the filtering on a background thread
                .collect { filteredList ->
                    // Update the filtered products in the LiveData
                    _filteredProductsLiveData.postValue(filteredList)
                }
        }
    }

}
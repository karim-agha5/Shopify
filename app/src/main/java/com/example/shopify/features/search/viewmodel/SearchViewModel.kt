package com.example.shopify.features.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(private val products: List<Product>?) : ViewModel() {
    private val TAG = "SearchViewModel"
    private val _filteredProductsLiveData = MutableLiveData<List<Product>>()
    val filteredProductsLiveData: LiveData<List<Product>> = _filteredProductsLiveData

    fun filterProducts(key: String) {
        viewModelScope.launch {
            // Use Kotlin coroutine operators to handle searching
            val searchQueryFlow = MutableStateFlow(key)

            searchQueryFlow
                .debounce(300) // Add a debounce delay to handle rapid text changes
                .filter { it.isNotBlank() } // Filter out empty search queries
                .distinctUntilChanged() // Only emit when the search query changes
                .flatMapLatest { query ->
                    flow {
                        val filteredList = products?.filter { product ->
                            product.title?.contains(query,true) == true ||
                                    product.vendor?.contains(query, true) == true ||
                                    product.product_type?.contains(query,true) == true ||
                                    product.tags?.contains(query,true) == true
                        }
                        emit(filteredList)
                    }
                }
                .flowOn(Dispatchers.Default) // Perform the filtering on a background thread
                .collect { filteredList ->
                    // Update the filtered products in the LiveData
                    Log.d(TAG, "filterProducts: ${filteredList?.size}")
                    _filteredProductsLiveData.postValue(filteredList)
                }
        }
    }
}

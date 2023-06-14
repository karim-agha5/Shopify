package com.example.shopify.features.products.view.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.home.repository.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductsViewModel(private val repositoryInterface: RepoInterface) : ViewModel() {

    val products = MutableStateFlow<ApiState>(ApiState.Loading)

    fun getProductsByCollection(collectionId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getProductsByCollection(collectionId)
                .catch { e -> products.value = ApiState.Failure(e) }.collect { data ->
                    products.value = ApiState.Success(data)
            }
        }
    }
}
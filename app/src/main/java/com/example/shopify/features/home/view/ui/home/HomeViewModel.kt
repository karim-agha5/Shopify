package com.example.shopify.features.home.view.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.home.repository.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repositoryInterface: RepoInterface): ViewModel() {

    val brands = MutableStateFlow<ApiState>(ApiState.Loading)
    val tenProducts = MutableStateFlow<ApiState>(ApiState.Loading)
    init {
        getStateFlowProducts()
        getTenProducts()
    }

    private fun getStateFlowProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getBrands()
                .catch { e -> brands.value = ApiState.Failure(e) }.collect { data ->
                    brands.value = ApiState.Success(data)
                }
        }
    }

    private fun getTenProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getLimitedProducts(10).catch { e -> tenProducts.value = ApiState.Failure(e) }.collect {
                data -> tenProducts.value = ApiState.Success(data)
            }
        }
    }
}
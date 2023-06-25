package com.example.shopify.features.home.view.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.util.ApiState
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.home.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repositoryInterface: IHomeRepository): ViewModel() {

    val brands = MutableStateFlow<ApiState>(ApiState.Loading)
    val tenProducts = MutableStateFlow<ApiState>(ApiState.Loading)
    val allProducts = MutableStateFlow(ApiState2.Loading as ApiState2<List<Product>>)
    init {
        getStateFlowProducts()
        getTenProducts()
        getAllProducts()
    }

     fun getStateFlowProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getBrands()
                .catch { e -> brands.value = ApiState.Failure(e) }.collect { data ->
                    brands.value = ApiState.Success(data)
                }
        }
    }

    fun getTenProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getLimitedProducts(10).catch { e -> tenProducts.value = ApiState.Failure(e) }.collect {
                data -> tenProducts.value = ApiState.Success(data)
            }
        }
    }

    private fun getAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getAllProducts()
                .collect {
                    allProducts.value = ApiState2.Success(it)
                }
        }
    }
}
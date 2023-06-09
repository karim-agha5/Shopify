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

    val stateFlow = MutableStateFlow<ApiState>(ApiState.Loading)

    init {
        getStateFlowProducts()
    }

    private fun getStateFlowProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getBrands()
                .catch { e -> stateFlow.value = ApiState.Failure(e) }.collect { data ->
                    stateFlow.value = ApiState.Success(data)
                }
        }
    }
}
package com.example.shopify.features.category.view.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.category.repository.ICategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CategoryViewModel (private val repositoryInterface: ICategoryRepository) : ViewModel() {

    val mainCategories = MutableStateFlow<ApiState>(ApiState.Loading)

    init {
        getStateFlowProducts()
    }


    private fun getStateFlowProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getMainCategories()
                .catch { e -> mainCategories.value = ApiState.Failure(e) }.collect { data ->
                    mainCategories.value = ApiState.Success(data)
                }
        }
    }
}
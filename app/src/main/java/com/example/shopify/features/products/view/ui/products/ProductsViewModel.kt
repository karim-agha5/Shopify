package com.example.shopify.features.products.view.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.products.repository.IProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductsViewModel(private val repositoryInterface: IProductsRepository) : ViewModel() {

    val products = MutableStateFlow<ApiState>(ApiState.Loading)
    var listOfOptions = listOf<String?>()
    var originalProducts = listOf<Product>()
    var sortedProducts = listOf<Product>()


    fun getProductsByCollection(collectionId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getProductsByCollection(collectionId)
                .catch { e -> products.value = ApiState.Failure(e) }.collect { data ->
                    products.value = ApiState.Success(data)
                    originalProducts = data
                    sortedProducts = data
                }
        }
    }

    fun getCollectionFilterOptions(collectionId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            listOfOptions = repositoryInterface.getFilterOptions(collectionId)
        }
    }

    fun filterProductsByProductType(productTypes: MutableList<String?>) {
        if (productTypes.contains("ALL")){
            sortedProducts = originalProducts
        } else {
            sortedProducts = originalProducts.filter { product ->
                productTypes.contains(product.product_type)
            }
            println("filter function ${sortedProducts.size}")
        }
    }

}
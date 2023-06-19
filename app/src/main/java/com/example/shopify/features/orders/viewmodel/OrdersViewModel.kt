package com.example.shopify.features.orders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.orders.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OrdersViewModel (private val repositoryInterface: IOrdersRepository): ViewModel() {

    val orders = MutableStateFlow<ApiState>(ApiState.Loading)

    fun getFlowOrders(customerId: CustomerId) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryInterface.getOrdersByCustomerId(customerId)
                .catch { e -> orders.value = ApiState.Failure(e) }.collect{
                    data -> orders.value = ApiState.Success(data)
                }
        }
    }
}
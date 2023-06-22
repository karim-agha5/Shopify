package com.example.shopify.features.checkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.checkout.data.ICheckoutRepository
import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val checkoutRepository: ICheckoutRepository
) : ViewModel() {

    private val _orderCheckoutState =
        MutableStateFlow(ApiState2.Loading as ApiState2<CheckoutOrderResponse>)

    val orderCheckoutState = _orderCheckoutState.asStateFlow()

    fun createOrder(body: CheckoutOrderRequest){
       viewModelScope.launch(Dispatchers.IO){
           val response = checkoutRepository.createOrder(body)
           response
               .catch {
                   _orderCheckoutState.value = ApiState2.Failure(it)
               }
               .collect{
                   _orderCheckoutState.value = ApiState2.Success(it)
               }
       }
    }
}
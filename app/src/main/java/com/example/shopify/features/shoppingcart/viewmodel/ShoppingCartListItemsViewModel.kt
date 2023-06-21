package com.example.shopify.features.shoppingcart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseBody
import com.example.shopify.core.util.ApiState2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ShoppingCartListItemsViewModel(
    private val shoppingCartRepository: IDraftOrderRepository
) : ViewModel() {

    private val _listItemsStateFlow =
        MutableStateFlow(ApiState2.Loading as ApiState2<ModifyDraftOrderResponseBody?>)

    val listItemsStateFlow = _listItemsStateFlow.asStateFlow()

    fun getShoppingCart(draftOrderId: String){
        viewModelScope.launch(Dispatchers.IO){
            val response = shoppingCartRepository.getShoppingCart(draftOrderId)
            response
                .catch {
                    _listItemsStateFlow.value = ApiState2.Failure(it)
                }
                .collect{
                    _listItemsStateFlow.value = ApiState2.Success(it.draftOrder)
                }
        }
    }

    fun modifyShoppingCart(draftOrderId: String,body: ModifyDraftOrderRequestBody){
        _listItemsStateFlow.value = ApiState2.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = shoppingCartRepository.modifyShoppingCart(draftOrderId,body)
            response
                .catch {
                    _listItemsStateFlow.value = ApiState2.Failure(it)
                }
                .collect{
                _listItemsStateFlow.value = ApiState2.Success(it.draftOrder)
            }
        }
    }
}
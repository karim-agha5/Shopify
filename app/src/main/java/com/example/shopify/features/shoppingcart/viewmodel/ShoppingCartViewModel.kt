package com.example.shopify.features.shoppingcart.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingCartViewModel(
    private val shoppingCartRepository: IDraftOrderRepository
) : ViewModel() {

    /*private val _listItemsStateFlow =
        MutableStateFlow(ApiState2.Loading as ApiState2<ModifyDraftOrderResponseLineItem>)
*/
    private val _listItemsStateFlow =
        MutableStateFlow<ModifyDraftOrderResponseBody?>(null)

    val listItemsStateFlow = _listItemsStateFlow.asStateFlow()

    suspend fun getShoppingCart(draftOrderId: String){
        val response = shoppingCartRepository.getShoppingCart(draftOrderId)
        _listItemsStateFlow.value = response.draftOrder
    }
}
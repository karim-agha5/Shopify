package com.example.shopify.features.shoppingcart.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopify.core.common.features.draftorder.data.IShoppingCartRepository
import com.example.shopify.core.common.features.draftorder.model.modification.response.GetDraftOrderResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingCartViewModel(
    private val shoppingCartRepository: IShoppingCartRepository
) : ViewModel() {

    /*private val _listItemsStateFlow =
        MutableStateFlow(ApiState2.Loading as ApiState2<ModifyCartResponseLineItem>)
*/
    private val _listItemsStateFlow =
        MutableStateFlow<GetDraftOrderResponse?>(null)

    val listItemsStateFlow = _listItemsStateFlow.asStateFlow()

    suspend fun getShoppingCart(draftOrderId: String){
        val response = shoppingCartRepository.getShoppingCart(draftOrderId)
        _listItemsStateFlow.value = response.draftOrder
    }
}
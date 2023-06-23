package com.example.shopify.features.shoppingcart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.shoppingcart.domain.DraftOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShoppingCartListItemsViewModel(
    private val shoppingCartRepository: IDraftOrderRepository,
    private val draftOrder: DraftOrder
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
                    draftOrder.submitOrderList(it.draftOrder.lineItems?.toMutableList() ?: mutableListOf())
                }
        }
    }

    /*fun modifyShoppingCart(draftOrderId: String,body: ModifyDraftOrderRequestBody){
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
    }*/

    fun removeOrder(customer: CustomerResponseInfo?,order: ModifyDraftOrderResponseLineItem) {
        /*orders.remove(order)
        modifyRemoteShoppingCart(null,-1)*/
        _listItemsStateFlow.value = ApiState2.Loading
        viewModelScope.launch(Dispatchers.IO){
            draftOrder
                .removeOrder(customer,order)
                .catch {
                    _listItemsStateFlow.value = ApiState2.Failure(it)
                }
                .collect{
                    _listItemsStateFlow.value = ApiState2.Success(it.draftOrder)
                    draftOrder.submitOrderList(it.draftOrder.lineItems?.toMutableList())
            }
        }
    }

    /*
    * Increments the actual quantity of an order and modifies the remote draft order
    * */
     fun incrementOrder(customer: CustomerResponseInfo?,order: ModifyDraftOrderResponseLineItem, position: Int) {
        val incrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.plus(1))
        //modifyRemoteShoppingCart(incrementedOrder,position)
        _listItemsStateFlow.value = ApiState2.Loading
        viewModelScope.launch(Dispatchers.IO){
            draftOrder
                .incrementOrder(customer, order, position)
                .catch {
                    _listItemsStateFlow.value = ApiState2.Failure(it)
                }
                .collect{
                    _listItemsStateFlow.value = ApiState2.Success(it.draftOrder)
                    draftOrder.submitOrderList(it.draftOrder.lineItems?.toMutableList())
                }
        }
    }

    fun decrementOrder(customer: CustomerResponseInfo?,order: ModifyDraftOrderResponseLineItem, position: Int) {
        val decrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.minus(1))
        //modifyRemoteShoppingCart(decrementedOrder,position)
        _listItemsStateFlow.value = ApiState2.Loading
        viewModelScope.launch(Dispatchers.IO){
            draftOrder
                .decrementOrder(customer, order, position)
                .catch {
                    _listItemsStateFlow.value = ApiState2.Failure(it)
                }
                .collect{
                    _listItemsStateFlow.value = ApiState2.Success(it.draftOrder)
                    draftOrder.submitOrderList(it.draftOrder.lineItems?.toMutableList())
                }
        }
    }
}
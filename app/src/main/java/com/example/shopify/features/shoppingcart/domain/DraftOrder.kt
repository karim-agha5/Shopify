package com.example.shopify.features.shoppingcart.domain

import android.util.Log
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.common.mappers.LineItemsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class DraftOrder(
    private val draftOrderRepositoryImpl: IDraftOrderRepository
)
{
    private val orders = mutableListOf<ModifyDraftOrderResponseLineItem>()

    suspend fun removeOrder(
        customer:CustomerResponseInfo?,
        order: ModifyDraftOrderResponseLineItem
    ) : Flow<ModifyDraftOrderResponse> {
        orders.remove(order)
        return modifyRemoteShoppingCart(customer,null,-1)
    }

    /*
    * Increments the actual quantity of an order and modifies the remote draft order
    * */
     suspend fun incrementOrder(
        customer:CustomerResponseInfo?,
        order: ModifyDraftOrderResponseLineItem,
        position: Int
    ) : Flow<ModifyDraftOrderResponse>{
        val incrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.plus(1))
        return modifyRemoteShoppingCart(customer,incrementedOrder,position)
    }

     suspend fun decrementOrder(
         customer:CustomerResponseInfo?,
         order: ModifyDraftOrderResponseLineItem,
         position: Int
     ) : Flow<ModifyDraftOrderResponse> {

        val decrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.minus(1))
        return modifyRemoteShoppingCart(customer,decrementedOrder,position)
    }

    private suspend fun modifyRemoteShoppingCart(
        customer:CustomerResponseInfo?,
        order: ModifyDraftOrderResponseLineItem?,
        position: Int
    ) : Flow<ModifyDraftOrderResponse> {
        /*
       * A work around to update each item's quantity to avoid multiple clicks on the same button.
       * Multiple clicks lead to items duplication in the recyclerview.
       * Adding an circular loading progress to each recyclerview item lead to unexpected behaviors.
       * */
        val tempList = orders.toMutableList()
        if(order != null) {
            tempList[position] = order
        }
        val requestLineItemsList = LineItemsMapper.fromResponseToRequestLineItems(tempList)
        val requestDraftOrder = ModifyDraftOrderRequestDraftOrder(
            null,
            //"test@hotmail.com",
            customer?.email ?: "",
            true,
            requestLineItemsList,
            null
        )
        val body = ModifyDraftOrderRequestBody(requestDraftOrder)
        return draftOrderRepositoryImpl.modifyShoppingCart(customer?.cartId?.toString() ?: "",body)
    }

     suspend fun clearShoppingCart(customer: CustomerResponseInfo?){
        val draftOrderState =
            draftOrderRepositoryImpl
                .getShoppingCart(customer?.cartId.toString() ?: "")
                .catch {
                    Log.i("Exception", "Inside ${this@DraftOrder::class.java} | getShoppingCart | " +
                            "${it.message}")
                }
                .collect{
                    val draft = it.draftOrder.lineItems
                    val lineItems: MutableList<ModifyDraftOrderResponseLineItem> =
                        mutableListOf(draft?.get(0)!!)
                    val requestLineItemsList = LineItemsMapper.fromResponseToRequestLineItems(lineItems.toList())
                    val requestDraftOrder = ModifyDraftOrderRequestDraftOrder(
                        null,
                        //"test@hotmail.com",
                        customer?.email ?: "",
                        true,
                        requestLineItemsList,
                        null
                    )
                    val body = ModifyDraftOrderRequestBody(requestDraftOrder)
                    draftOrderRepositoryImpl
                        .modifyShoppingCart(customer?.cartId?.toString() ?: "",body)
                        .catch {
                            Log.i("Exception", "Inside ${this@DraftOrder::class.java} | getShoppingCart | " +
                                    "${it.message}")
                        }
                        .collect {
                            Log.i("Exception", "clearShoppingCart: success")
                        }
                }
    }

    fun submitOrderList(orders: MutableList<ModifyDraftOrderResponseLineItem>?){
        this.orders.clear()
        this.orders.addAll(orders ?: mutableListOf())
    }
}
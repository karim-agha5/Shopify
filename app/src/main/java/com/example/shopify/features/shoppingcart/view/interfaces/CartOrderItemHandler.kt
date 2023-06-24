package com.example.shopify.features.shoppingcart.view.interfaces

import com.example.shopify.core.common.features.draftorder.model.Order
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem

interface CartOrderItemHandler {
    fun removeOrder(order: ModifyDraftOrderResponseLineItem)
    fun incrementOrder(order: ModifyDraftOrderResponseLineItem,position: Int)
    fun decrementOrder(order: ModifyDraftOrderResponseLineItem,position: Int)
}
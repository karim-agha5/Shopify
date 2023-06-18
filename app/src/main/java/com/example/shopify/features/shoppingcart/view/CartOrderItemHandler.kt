package com.example.shopify.features.shoppingcart.view

import com.example.shopify.core.common.features.draftorder.model.Order

interface CartOrderItemHandler {
    fun removeOrder(order: Order)
}
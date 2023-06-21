package com.example.shopify.features.orders.view

import com.example.shopify.features.orders.model.model_response.OrderResponseData

interface IOrderDetails {
    fun navigateToOrderDetails(order : OrderResponseData)
}
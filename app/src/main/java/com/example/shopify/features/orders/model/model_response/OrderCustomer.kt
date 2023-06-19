package com.example.shopify.features.orders.model.model_response

data class OrderCustomer(
    val created_at: String,
    val currency: String,
    val default_address: DefaultAddress,
    val email: String,
    val first_name: String,
    val id: Long,
    val last_name: String,
    val phone: String,
    val updated_at: String,
    val verified_email: Boolean
)
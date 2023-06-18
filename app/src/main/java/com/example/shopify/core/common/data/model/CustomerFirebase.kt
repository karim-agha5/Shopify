package com.example.shopify.core.common.data.model

data class CustomerFirebase(
    val name: String,
    val email: String,
    val customerId: Long,
    val cartId: Long,
    val wishListId: Long,
    val coupon: String
)

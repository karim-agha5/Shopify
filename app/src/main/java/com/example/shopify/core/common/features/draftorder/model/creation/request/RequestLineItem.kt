package com.example.shopify.core.common.features.draftorder.model.creation.request

data class RequestLineItem(
    val title: String,
    val price: String,
    val quantity: Int
)

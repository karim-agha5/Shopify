package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class DiscountRegistration(
    val description: String,
    @SerializedName("value_type") val valueType: String,
    val value: String,
    val amount: String,
    val title: String
)

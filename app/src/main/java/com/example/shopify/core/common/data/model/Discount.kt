package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class Discount(
    val description: String?,
    val title: String?,
    @SerializedName("value_type")
    val valueType: String?,
    val value: Int?,
    val amount: String?
)

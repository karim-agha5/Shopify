package com.example.shopify.core.common.features.draftorder.model.creation.response

import com.google.gson.annotations.SerializedName

data class Discount(
    val description: String?,
    @SerializedName("value_type")
    val valueType: String = "fixed_amount",
    val value: String?,
    val amount: String?,
    val title: String?
)
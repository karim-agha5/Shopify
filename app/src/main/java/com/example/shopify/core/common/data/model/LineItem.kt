package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class LineItem(
    val id: Long?,
    @SerializedName("variant_id") val variantId: Long?,
    @SerializedName("product_id") val productId: Long?,
    val title: String?,
    @SerializedName("variant_title") val variantTitle: String?,
    val vendor: String?,
    val quantity: Int?,
    @SerializedName("applied_discount") val appliedDiscount: Discount?,
    val name: String?,
    val custom: Boolean?,
    val price: String?,
)

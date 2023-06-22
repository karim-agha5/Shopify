package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CheckoutLineItem(
    val id: Long?,
    @SerializedName("variant_id")
    val variantId: Long?,
    @SerializedName("product_id")
    val productId: Long?,
    val title: String?,
    @SerializedName("variant_title")
    val variantTitle: String?,
    val quantity: Int?,
    val name: String?,
    val price: String?,
    val currency: String?
)

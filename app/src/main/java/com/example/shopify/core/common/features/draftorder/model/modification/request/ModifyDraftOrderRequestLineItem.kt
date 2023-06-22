package com.example.shopify.core.common.features.draftorder.model.modification.request

import com.example.shopify.core.common.features.draftorder.model.Discount
import com.google.gson.annotations.SerializedName

data class ModifyDraftOrderRequestLineItem(
    val id: Long?,
    @SerializedName("variant_id") val variantId: Long?,
    @SerializedName("product_id") val productId: Long?,
    val title: String?,
    @SerializedName("variant_title") val variantTitle: String?,
    val quantity: Int?,
    @SerializedName("requires_shipping") val requiresShipping: Boolean?,
    @SerializedName("fulfillment_service") val fulfillmentService: String? = "manual",
    @SerializedName("applied_discount") val appliedDiscount: Discount?,
    val properties: List<Any?>?,
    val price: String?
)

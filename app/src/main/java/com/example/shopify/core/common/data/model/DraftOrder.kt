package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class DraftOrder(
    val id: Long?,
    val email: String?,
    val currency: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val name: String?,
    val status: String?,
    @SerializedName("line_items") val lineItems: List<LineItem>?,
    @SerializedName("applied_discount") val appliedDiscount: Discount?,
    @SerializedName("order_id") val orderId: Long?,
    val tags: String?,
    @SerializedName("total_price") val totalPrice: String?,
    @SerializedName("subtotal_price") val subtotalPrice: String?,
    @SerializedName("payment_terms") val paymentTerms: String?,
    val customer: CustomerResponseInfo?
)

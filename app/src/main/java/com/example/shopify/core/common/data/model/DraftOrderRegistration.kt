package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class DraftOrderRegistration(
    @SerializedName("line_items") val lineItems: List<LineItemRegistration>,
    @SerializedName("applied_discount") val appliedDiscount: DiscountRegistration,
    val customer: Int,
    @SerializedName("use_customer_default_address") val useCustomerDefaultAddress: Boolean
)
package com.example.shopify.core.common.features.draftorder.model.creation.request

import com.example.shopify.core.common.features.draftorder.model.creation.response.Discount
import com.google.gson.annotations.SerializedName

data class RequestDraftOrder(
    @SerializedName("line_items")
    val lineItems: List<RequestLineItem>,
    @SerializedName("applied_discount")
    val appliedDiscount: Discount?,
    val customer: RequestCustomer,
    @SerializedName("use_customer_default_address")
    val useCustomerDefaultAddress: Boolean = true
)

package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CheckoutOrder(
    @SerializedName("line_items")
    val lineItems: List<CheckoutLineItem>,
    val customer: CheckoutCustomer,
    val currency: String?,
    @SerializedName("billing_address")
    val billingAddress: CheckoutBillingAddress,
    @SerializedName("discount_codes")
    val discountCodes: List<CheckoutDiscountCode>?,
    @SerializedName("shipping_lines")
    val shippingLines: List<CheckoutShippingLines>
)

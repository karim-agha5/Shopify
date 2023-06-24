package com.example.shopify.features.checkout.paymentgateway.stripe.model

data class StripeCustomer(
    val id: String?,
    val balance: Double,
    val email: String?,
    val currency: String?
)

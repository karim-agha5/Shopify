package com.example.shopify.features.checkout.paymentgateway.stripe.model

import com.google.gson.annotations.SerializedName

data class StripePaymentIntentResponse(
    val id: String?,
    val amount: Long?,
    @SerializedName("client_secret")
    val clientSecret: String?
)

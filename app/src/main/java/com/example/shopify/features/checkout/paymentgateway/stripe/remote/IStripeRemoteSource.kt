package com.example.shopify.features.checkout.paymentgateway.stripe.remote

import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomer
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomerEphemeralKey
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripePaymentIntentResponse
import retrofit2.http.Field


interface IStripeRemoteSource {
    suspend fun createStripeCustomer() : StripeCustomer?
    suspend fun getEphemeralKey(@Field("customer") customerId: String) : StripeCustomerEphemeralKey?
    suspend fun submitPaymentIntent(
        customerId: String,
        amount: Long,
        currency: String,
        isEnabled: Boolean = true
    ) : StripePaymentIntentResponse?
}
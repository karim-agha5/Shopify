package com.example.shopify.features.checkout.paymentgateway.stripe

import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomer
import retrofit2.http.Header
import retrofit2.http.POST

interface StripeService {

    @POST("customers")
    suspend fun createStripeCustomer() : StripeCustomer?
}
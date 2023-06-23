package com.example.shopify.features.checkout.paymentgateway.stripe.service

import com.example.shopify.core.util.Constants
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomer
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomerEphemeralKey
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripePaymentIntentResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface StripeService {

    @POST("customers")
    suspend fun createStripeCustomer() : StripeCustomer?

    @FormUrlEncoded
    @POST("ephemeral_keys")
    @Headers("${Constants.STRIPE_VERSION_HEADER_KEY}:${Constants.STRIPE_VERSION_HEADER_VALUE}")
    suspend fun getEphemeralKey(@Field("customer") customerId: String) : StripeCustomerEphemeralKey?

    @FormUrlEncoded
    @POST("payment_intents")
    suspend fun submitPaymentIntent(
        @Field("customer") customerId: String,
        @Field("amount") amount: Long,
        @Field("currency") currency: String,
        @Field("automatic_payment_methods[enabled]") isEnabled: Boolean = true
    ) : StripePaymentIntentResponse?
}
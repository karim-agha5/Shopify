package com.example.shopify.features.checkout.paymentgateway.stripe.remote

import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomer
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripeCustomerEphemeralKey
import com.example.shopify.features.checkout.paymentgateway.stripe.model.StripePaymentIntentResponse
import com.example.shopify.features.checkout.paymentgateway.stripe.service.StripeService

class StripeRemoteSourceImpl(
    private val service: StripeService
) : IStripeRemoteSource{
    override suspend fun createStripeCustomer(): StripeCustomer? {
        return service.createStripeCustomer()
    }

    override suspend fun getEphemeralKey(customerId: String): StripeCustomerEphemeralKey? {
        return service.getEphemeralKey(customerId)
    }

    override suspend fun submitPaymentIntent(
        customerId: String,
        amount: Long,
        currency: String,
        isEnabled: Boolean
    ): StripePaymentIntentResponse? {
        return service.submitPaymentIntent(customerId, amount, currency, isEnabled)
    }

}
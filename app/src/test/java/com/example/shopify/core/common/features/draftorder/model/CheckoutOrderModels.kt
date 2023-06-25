package com.example.shopify.core.common.features.draftorder.model

import com.example.shopify.core.common.data.model.CheckoutBillingAddress
import com.example.shopify.core.common.data.model.CheckoutCustomer
import com.example.shopify.core.common.data.model.CheckoutOrder
import com.example.shopify.features.checkout.model.CheckoutOrderRequest


val fakeCheckoutOrder = CheckoutOrder(
    listOf(),
    CheckoutCustomer(51,""),
    null,
    CheckoutBillingAddress(null,null,null,null,null,null),
    null,
    listOf()
)

val fakeCheckoutOrderRequest = CheckoutOrderRequest(
    fakeCheckoutOrder
)
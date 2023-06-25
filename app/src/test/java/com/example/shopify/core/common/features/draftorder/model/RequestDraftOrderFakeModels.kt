package com.example.shopify.core.common.features.draftorder.model

import com.example.shopify.core.common.data.model.CustomerResponseAddress
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem


val fakeModifyDraftOrderRequestDraftOrder = ModifyDraftOrderRequestDraftOrder(
    "",
    "",
    true,
    listOf(),
    null
)

val fakeModifyDraftOrderRequestBody = ModifyDraftOrderRequestBody(
    fakeModifyDraftOrderRequestDraftOrder
)




val fakeCustomerResponseInfo = CustomerResponseInfo(
    id = 5678,
    cartId = 1128025194815,
    wishListId = 9012,
    null,
    email = "test@hotmail.com",
    acceptsMarketing = true,
    createdAt = "2023-06-24T10:30:00Z",
    updatedAt = "2023-06-25T10:30:00Z",
    firstName = "John",
    lastName = "Doe",
    ordersCount = 5,
    state = "enabled",
    totalSpent = "100.00",
    lastOrderId = 4321,
    verifiedEmail = true,
    multipassIdentifier = "MULTIPASS123",
    lastOrderName = "Order name",
    currency = "USD",
    phone = "555-555-5555",
    addresses = listOf(),
    acceptsMarketingUpdatedAt = "2023-06-24T10:30:00Z",
    marketingOptInLevel = "single_opt_in",
    taxExemptions = null,
    defaultAddress = null
)

val fakeModifyDraftOrderResponseLineItem = ModifyDraftOrderResponseLineItem(
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null
)
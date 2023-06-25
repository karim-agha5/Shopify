package com.example.shopify.core.common.features.draftorder.model

import com.example.shopify.core.common.features.draftorder.model.Discount
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseAddress
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseCustomer
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseLineItem
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseAddress
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseConsent
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseCustomer
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem

val lineItems = listOf(
    CreateDraftOrderResponseLineItem(
        id = 123,
        variant_id = null,
        product_id = null,
        title = "Sample Product",
        variant_title = null,
        sku = null,
        vendor = null,
        quantity = 1,
        requires_shipping = true,
        taxable = true,
        gift_card = false,
        fulfillment_service = "Sample Fulfillment",
        grams = 100,
        tax_lines = null,
        applied_discount = null,
        name = "Sample Product",
        properties = null,
        custom = false,
        price = "10.00",
        admin_graphql_api_id = "sample_graphql_api_id"
    )
)

val shippingAddress = CreateDraftOrderResponseAddress(
    first_name = "John",
    address1 = "123 Sample Street",
    phone = "1234567890",
    city = "Sample City",
    zip = "12345",
    province = "Sample Province",
    country = "Sample Country",
    lastName = "Doe",
    address2 = null,
    company = null,
    latitude = null,
    longitude = null,
    name = "John Doe",
    countryCode = "US",
    provinceCode = "SP"
)

val billingAddress = CreateDraftOrderResponseAddress(
    first_name = "John",
    address1 = "456 Sample Street",
    phone = "0987654321",
    city = "Another City",
    zip = "54321",
    province = "Another Province",
    country = "Another Country",
    lastName = "Doe",
    address2 = null,
    company = null,
    latitude = null,
    longitude = null,
    name = "John Doe",
    countryCode = "US",
    provinceCode = "AP"
)

val appliedDiscount = Discount(
    description = "Sample Discount",
    valueType = "fixed_amount",
    value = "2.00",
    amount = "2.00",
    title = "Sample Discount"
)

val customer = CreateDraftOrderResponseCustomer(
    id = 456,
    email = "john.doe@example.com",
    accepts_marketing = true,
    created_at = "2023-06-25T10:00:00Z",
    updated_at = "2023-06-25T11:00:00Z",
    first_name = "John",
    last_name = "Doe",
    orders_count = 5,
    state = "active",
    total_spent = "100.00",
    last_order_id = null,
    note = null,
    verified_email = true,
    multipass_identifier = null,
    tax_exempt = false,
    tags = "",
    last_order_name = null,
    currency = "USD",
    phone = "1234567890",
    accepts_marketing_updated_at = "2023-06-25T10:30:00Z",
    marketing_opt_in_level = null,
    tax_exemptions = null,
    email_marketing_CreateDraftOrderResponse_consent = null,
    sms_marketing_CreateDraftOrderResponse_consent = null,
    admin_graphql_api_id = "sample_graphql_api_id",
    default_CreateDraftOrderResponse_address = null
)

val fakeModifyDraftOrderResponseBody = ModifyDraftOrderResponseBody(
    id = 575,
    note = "",
    email = "",
    taxesIncluded = true,
    currency = "",
    invoiceSentAt = "",
    createdAt = "",
    updatedAt = "",
    taxExempt = false,
    completedAt = null,
    name = "",
    status = "",
    lineItems = listOf(
        ModifyDraftOrderResponseLineItem(
            id = 1,
            variantId = 5678,
            productId = 1234,
            title = "",
            variantTitle = "",
            sku = "",
            vendor = "",
            requestedQuantity = 2,
            requiresShipping = true,
            taxable = true,
            giftCard = false,
            fulfillmentService = "",
            grams = 100,
            taxLines = null,
            appliedDiscount = null,
            name = "",
            properties = null,
            custom = false,
            price = "10.00"
        )
    ),
    shippingAddress = ModifyDraftOrderResponseAddress(
        firstName = "",
        address1 = "",
        phone = "",
        city = "",
        zip = "",
        province = "",
        country = "",
        lastName = "",
        address2 = null,
        company = null,
        latitude = null,
        longitude = null,
        name = "",
        countryCode = "",
        provinceCode = ""
    ),
    billingAddress = ModifyDraftOrderResponseAddress(
        firstName = "",
        address1 = "",
        phone = "",
        city = "",
        zip = "",
        province = "",
        country = "",
        lastName = "",
        address2 = null,
        company = null,
        latitude = null,
        longitude = null,
        name = "",
        countryCode = "",
        provinceCode = ""
    ),
    invoiceUrl = null,
    appliedDiscount = null,
    orderId = null,
    shippingLine = null,
    taxLines = null,
    tags = "",
    noteAttributes = null,
    totalPrice = "",
    subtotalPrice = "",
    totalTax = "",
    paymentTerms = null,
    adminGraphqlApiId = "",
    customer = ModifyDraftOrderResponseCustomer(
        id = 5678,
        email = "",
        acceptsMarketing = true,
        createdAt = "",
        updatedAt = "",
        firstName = "",
        lastName = "",
        ordersCount = 5,
        state = "",
        totalSpent = "",
        lastOrderId = 4321,
        note = null,
        verifiedEmail = true,
        multipassIdentifier = null,
        taxExempt = false,
        tags = "",
        lastOrderName = "",
        currency = "",
        phone = "",
        acceptsMarketingUpdatedAt = "",
        marketingOptInLevel = null,
        taxExemptions = null,
        emailMarketingConsent = ModifyDraftOrderResponseConsent(
            state = "",
            optInLevel = "",
            consentUpdatedAt = "",
            consentCollectedFrom = ""
        ),
        smsMarketingConsent = null,
        adminGraphqlApiId = "",
        defaultAddress = ModifyDraftOrderResponseAddress(
            firstName = "",
            address1 = "",
            phone = "",
            city = "",
            zip = "",
            province = "",
            country = "",
            lastName = "",
            address2 = null,
            company = null,
            latitude = null,
            longitude = null,
            name = "",
            countryCode = "",
            provinceCode = ""
        )
    )
)


val fakeModifyDraftOrderResponse =
    ModifyDraftOrderResponse(
        fakeModifyDraftOrderResponseBody
    )
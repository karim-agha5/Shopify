package com.example.shopify.common.features.draftorder.model

import com.example.shopify.core.common.data.model.ImageX
import com.example.shopify.core.common.data.model.Option
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.Variant
import com.example.shopify.core.common.features.draftorder.model.Discount
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseAddress
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseCustomer
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseDraftOrder
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseLineItem
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseAddress
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseConsent
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseCustomer
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem

val products = listOf(
    Product(
        id = 1,
        image = ImageX(100, 1, 0, 1, "https://example.com/image1.jpg", "2023-06-25", listOf(1, 2, 3), 200),
        images = listOf(
            ImageX(150, 2, 0, 1, "https://example.com/image2.jpg", "2023-06-25", listOf(1, 2, 3), 250),
            ImageX(200, 3, 0, 1, "https://example.com/image3.jpg", "2023-06-25", listOf(1, 2, 3), 300)
        ),
        options = listOf(
            Option(1, "Color", 0, 1, listOf("Red", "Blue", "Green")),
            Option(2, "Size", 1, 1, listOf("S", "M", "L"))
        ),
        product_type = "Clothing",
        status = "Available",
        tags = "tag1, tag2, tag3",
        title = "Product 1",
        variants = listOf(
            Variant(
                fulfillment_service = "Service",
                grams = 100,
                id = 1,
                image_id = null,
                inventory_item_id = 1001,
                inventory_management = "Management",
                inventory_policy = "Policy",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Option 1",
                option2 = "Option 2",
                option3 = null,
                position = 1,
                price = 9.99,
                product_id = 123,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Variant 1",
                updated_at = "2023-06-25",
                weight = 0.5,
                weight_unit = "kg"
            )
        ),
        vendor = "Vendor 1",
        isFav = false,
        rating = "4.5"
    ),
    Product(
        id = 2,
        image = ImageX(120, 4, 0, 2, "https://example.com/image4.jpg", "2023-06-25", listOf(4, 5), 180),
        images = listOf(
            ImageX(180, 5, 0, 2, "https://example.com/image5.jpg", "2023-06-25", listOf(4, 5), 270)
        ),
        options = listOf(
            Option(3, "Size", 0, 2, listOf("S", "M", "L"))
        ),
        product_type = "Electronics",
        status = "Available",
        tags = "tag4, tag5",
        title = "Product 2",
        variants = listOf(
            Variant(
                fulfillment_service = "Service",
                grams = 100,
                id = 1,
                image_id = null,
                inventory_item_id = 1001,
                inventory_management = "Management",
                inventory_policy = "Policy",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Option 1",
                option2 = "Option 2",
                option3 = null,
                position = 1,
                price = 9.99,
                product_id = 123,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Variant 1",
                updated_at = "2023-06-25",
                weight = 0.5,
                weight_unit = "kg"
            )
        ),
        vendor = "Vendor 2",
        isFav = true,
        rating = "4.0"
    )
)


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
    tags = "sample_tags",
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
    id = 1234,
    note = "This is a note",
    email = "example@example.com",
    taxesIncluded = true,
    currency = "USD",
    invoiceSentAt = "2023-06-24T10:30:00Z",
    createdAt = "2023-06-23T10:30:00Z",
    updatedAt = "2023-06-24T10:30:00Z",
    taxExempt = false,
    completedAt = null,
    name = "Draft order",
    status = "draft",
    lineItems = listOf(
        ModifyDraftOrderResponseLineItem(
            id = 1,
            variantId = 5678,
            productId = 1234,
            title = "Product title",
            variantTitle = "Variant title",
            sku = "ABCD1234",
            vendor = "Vendor name",
            requestedQuantity = 2,
            requiresShipping = true,
            taxable = true,
            giftCard = false,
            fulfillmentService = "Manual",
            grams = 100,
            taxLines = null,
            appliedDiscount = null,
            name = "Line item name",
            properties = null,
            custom = false,
            price = "10.00"
        )
    ),
    shippingAddress = ModifyDraftOrderResponseAddress(
        firstName = "John",
        address1 = "123 Main St",
        phone = "555-555-5555",
        city = "Anytown",
        zip = "12345",
        province = "NY",
        country = "United States",
        lastName = "Doe",
        address2 = null,
        company = null,
        latitude = null,
        longitude = null,
        name = "John Doe",
        countryCode = "US",
        provinceCode = "NY"
    ),
    billingAddress = ModifyDraftOrderResponseAddress(
        firstName = "John",
        address1 = "123 Main St",
        phone = "555-555-5555",
        city = "Anytown",
        zip = "12345",
        province = "NY",
        country = "United States",
        lastName = "Doe",
        address2 = null,
        company = null,
        latitude = null,
        longitude = null,
        name = "John Doe",
        countryCode = "US",
        provinceCode = "NY"
    ),
    invoiceUrl = null,
    appliedDiscount = null,
    orderId = null,
    shippingLine = null,
    taxLines = null,
    tags = "tag1,tag2",
    noteAttributes = null,
    totalPrice = "20.00",
    subtotalPrice = "20.00",
    totalTax = "2.00",
    paymentTerms = null,
    adminGraphqlApiId = "gid://shopify/DraftOrder/1234",
    customer = ModifyDraftOrderResponseCustomer(
        id = 5678,
        email = "customer@example.com",
        acceptsMarketing = true,
        createdAt = "2023-06-22T10:30:00Z",
        updatedAt = "2023-06-24T10:30:00Z",
        firstName = "Jane",
        lastName = "Doe",
        ordersCount = 5,
        state = "enabled",
        totalSpent = "100.00",
        lastOrderId = 4321,
        note = null,
        verifiedEmail = true,
        multipassIdentifier = null,
        taxExempt = false,
        tags = "tag1,tag2",
        lastOrderName = "Order name",
        currency = "USD",
        phone = "555-555-5555",
        acceptsMarketingUpdatedAt = "2023-06-23T10:30:00Z",
        marketingOptInLevel = null,
        taxExemptions = null,
        emailMarketingConsent = ModifyDraftOrderResponseConsent(
            state = "granted",
            optInLevel = "implicit",
            consentUpdatedAt = "2023-06-23T10:30:00Z",
            consentCollectedFrom = "Webstore"
        ),
        smsMarketingConsent = null,
        adminGraphqlApiId = "gid://shopify/Customer/5678",
        defaultAddress = ModifyDraftOrderResponseAddress(
            firstName = "Jane",
            address1 = "456 Main St",
            phone = "555-555-5555",
            city = "Anytown",
            zip = "12345",
            province = "NY",
            country = "United States",
            lastName = "Doe",
            address2 = null,
            company = null,
            latitude = null,
            longitude = null,
            name = "Jane Doe",
            countryCode = "US",
            provinceCode = "NY"
        )
    )
)


val fakeModifyDraftOrderResponse =
    ModifyDraftOrderResponse(
        fakeModifyDraftOrderResponseBody
    )

val draftOrder = CreateDraftOrderResponseDraftOrder(
    id = 789,
    note = null,
    email = "john.doe@example.com",
    taxes_included = true,
    currency = "USD",
    invoice_sent_at = null,
    created_at = "2023-06-25T12:00:00Z",
    updated_at = "2023-06-25T13:00:00Z",
    tax_exempt = false,
    completed_at = null,
    name = "Sample Draft Order",
    status = "open",
    line_items = lineItems,
    shipping_CreateDraftOrderResponse_address = shippingAddress,
    billing_CreateDraftOrderResponse_address = billingAddress,
    invoice_url = "https://example.com/invoice",
    applied_discount = appliedDiscount,
    order_id = null,
    shipping_line = null,
    tax_lines = null,
    tags = "sample_tags",
    note_attributes = null,
    total_price = "8.00",
    subtotal_price = "10.00",
    total_tax = "2.00",
    payment_terms = null,
    admin_graphql_api_id = "sample_graphql_api_id",
    customer = customer
)
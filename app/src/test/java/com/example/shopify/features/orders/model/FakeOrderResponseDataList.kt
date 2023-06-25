package com.example.shopify.features.orders.model

import com.example.shopify.core.common.data.model.CheckoutBillingAddress
import com.example.shopify.core.common.data.model.CheckoutShippingLines
import com.example.shopify.features.orders.model.model_response.DefaultAddress
import com.example.shopify.features.orders.model.model_response.DiscountApplication
import com.example.shopify.features.orders.model.model_response.LineItem
import com.example.shopify.features.orders.model.model_response.OrderCustomer
import com.example.shopify.features.orders.model.model_response.OrderResponseData

private val defaultAddress = DefaultAddress(
    "address1",
    "city",
    "country",
    "countryCode",
    "countryName",
    6516161616516,
    true,
    "firstName",
    6516516161651651,
    "lastName",
    "name",
    "+20107465684651",
    "province",
    "provinceCode"
)

private val orderCustomer = OrderCustomer(
    "24/6/23",
    "EGP",
    defaultAddress,
    "email@example.com",
    "first name",
    516516516161,
    "last name",
    "+201765646651",
    "24/6/2023",
    true
)

private val discountApplication = DiscountApplication(
    "targetType",
    "type",
    "value",
    "valueType",
    "allocated",
    "selected",
    "title",
    "example description"
)

val lineItem = LineItem(
    fulfillable_quantity = 10,
    fulfillment_service = "Sample Fulfillment Service",
    gift_card = false,
    grams = 500,
    id = 12345,
    name = "Sample Line Item",
    price = "9.99",
    product_exists = true,
    product_id = 67890,
    properties = null,
    quantity = 2,
    requires_shipping = true,
    sku = "ABC123",
    taxable = true,
    title = "Sample Title",
    total_discount = "5.00",
    variant_id = 54321,
    variant_inventory_management = "Sample Inventory Management",
    variant_title = "Sample Variant Title",
    vendor = "Sample Vendor"
)

private val billingAddress = CheckoutBillingAddress(
    "first name", "last name", "address1", "+20157150051", "city", "country"
)

private val shippingAddress = CheckoutShippingLines("COD", 200.00, "STANDARD")

val fakeOrderResponseDataList: List<OrderResponseData> = listOf(
    OrderResponseData(
        true,
        "contactEmail.com",
        "24/6/2023",
        "EGP",
        "150.00",
        orderCustomer,
        "email@example.com",
        "paid",
        351351351331384,
        listOf(discountApplication),
        listOf(lineItem),
        "name",
        1760,
        "EGP",
        "24/6/2023",
        "170.00",
        billingAddress,
        listOf(shippingAddress)
    )
)
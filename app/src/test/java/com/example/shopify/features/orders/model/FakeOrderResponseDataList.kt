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

private val lineItem = LineItem(
    listOf("any"),
    10,
    "paid",
    "any",
    true,
    100,
    100050007000,
    "name",
    "170.00",
    true,
    212451651351,
    listOf("properties"),
    10,
    true,
    "sku?",
    listOf("tax lines"),
    true,
    "title",
    "50",
    453452453834537,
    "shopify",
    "OS/black",
    "ADIDAS"
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
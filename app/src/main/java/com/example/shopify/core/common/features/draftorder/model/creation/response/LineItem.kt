package com.example.shopify.core.common.features.draftorder.model.creation.response

data class LineItem(
    val id: Long?,
    val variant_id: Any?,
    val product_id: Any?,
    val title: String?,
    val variant_title: Any?,
    val sku: Any?,
    val vendor: Any?,
    val quantity: Int?,
    val requires_shipping: Boolean?,
    val taxable: Boolean?,
    val gift_card: Boolean?,
    val fulfillment_service: String?,
    val grams: Int?,
    val tax_lines: List<Any?>?,
    val applied_discount: Any?,
    val name: String?,
    val properties: List<Any?>?,
    val custom: Boolean?,
    val price: String?,
    val admin_graphql_api_id: String?
)
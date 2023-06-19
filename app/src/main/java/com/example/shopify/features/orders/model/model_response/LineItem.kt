package com.example.shopify.features.orders.model.model_response

data class LineItem(
    val duties: List<Any>,
    val fulfillable_quantity: Int,
    val fulfillment_service: String,
    val fulfillment_status: Any,
    val gift_card: Boolean,
    val grams: Int,
    val id: Long,
    val name: String,
    val price: String,
    val product_exists: Boolean,
    val product_id: Long,
    val properties: List<Any>,
    val quantity: Int,
    val requires_shipping: Boolean,
    val sku: String,
    val tax_lines: List<Any>,
    val taxable: Boolean,
    val title: String,
    val total_discount: String,
    val variant_id: Long,
    val variant_inventory_management: String,
    val variant_title: String,
    val vendor: String
)
package com.example.shopify.core.common.features.draftorder.model.creation.response


data class ResponseDraftOrder(
    val id: Long?,
    val note: String?,
    val email: String?,
    val taxes_included: Boolean?,
    val currency: String?,
    val invoice_sent_at: String?,
    val created_at: String?,
    val updated_at: String?,
    val tax_exempt: Boolean?,
    val completed_at: String?,
    val name: String?,
    val status: String?,
    val line_items: List<LineItem>?,
    val shipping_address: Address?,
    val billing_address: Address?,
    val invoice_url: String?,
    val applied_discount: Discount?,
    val order_id: Long?,
    val shipping_line: Any?,
    val tax_lines: List<Any?>?,
    val tags: String?,
    val note_attributes: List<Any?>?,
    val total_price: String?,
    val subtotal_price: String?,
    val total_tax: String?,
    val payment_terms: Any?,
    val admin_graphql_api_id: String?,
    val customer: ResponseCustomer?
)
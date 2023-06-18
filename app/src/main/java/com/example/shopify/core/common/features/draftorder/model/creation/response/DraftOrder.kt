package com.example.shopify.core.common.features.draftorder.model.creation.response

import com.example.shopify.core.common.features.draftorder.model.Discount


data class CreateDraftOrderResponseDraftOrder(
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
    val line_items: List<CreateDraftOrderResponseLineItem>?,
    val shipping_CreateDraftOrderResponse_address: CreateDraftOrderResponseAddress?,
    val billing_CreateDraftOrderResponse_address: CreateDraftOrderResponseAddress?,
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
    val customer: CreateDraftOrderResponseCustomer?
)
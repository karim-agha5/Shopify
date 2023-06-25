package com.example.shopify.core.common.features.draftorder.data.remote

import com.example.shopify.core.common.features.draftorder.model.appliedDiscount
import com.example.shopify.core.common.features.draftorder.model.billingAddress
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseDraftOrder
import com.example.shopify.core.common.features.draftorder.model.customer
import com.example.shopify.core.common.features.draftorder.model.fakeModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.lineItems
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.shippingAddress

class FakeDraftOrderRemoteSource : IDraftOrderRemoteSource {
    override suspend fun createShoppingCart(body: CreateDraftOrderRequestBody): CreateDraftOrderResponse {
        val fakeCreateDraftOrderResponseDraftOrder = CreateDraftOrderResponseDraftOrder(
            id = 575,
            note = null,
            email = "",
            taxes_included = true,
            currency = "",
            invoice_sent_at = null,
            created_at = "",
            updated_at = "",
            tax_exempt = false,
            completed_at = null,
            name = "",
            status = "",
            line_items = lineItems,
            shipping_CreateDraftOrderResponse_address = shippingAddress,
            billing_CreateDraftOrderResponse_address = billingAddress,
            invoice_url = "",
            applied_discount = appliedDiscount,
            order_id = null,
            shipping_line = null,
            tax_lines = null,
            tags = "",
            note_attributes = null,
            total_price = "",
            subtotal_price = "",
            total_tax = "",
            payment_terms = null,
            admin_graphql_api_id = "",
            customer = customer
        )
        val fakeCreateDraftOrderResponse = CreateDraftOrderResponse(fakeCreateDraftOrderResponseDraftOrder)
        return fakeCreateDraftOrderResponse
    }

    override suspend fun getShoppingCart(draftOrderId: String): ModifyDraftOrderResponse {
        return fakeModifyDraftOrderResponse
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): ModifyDraftOrderResponse {
        return fakeModifyDraftOrderResponse
    }
}
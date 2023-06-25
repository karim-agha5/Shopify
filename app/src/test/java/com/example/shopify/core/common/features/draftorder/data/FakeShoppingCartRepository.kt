package com.example.shopify.core.common.features.draftorder.data

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeShoppingCartRepository : IDraftOrderRepository {
    override suspend fun createShoppingCart(body: CreateDraftOrderRequestBody): CreateDraftOrderResponse {

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

        val fakeResponse = CreateDraftOrderResponse(draftOrder)
        return fakeResponse
    }

    override suspend fun getShoppingCart(draftOrderId: String): Flow<ModifyDraftOrderResponse> {
        return flow{
            emit(fakeModifyDraftOrderResponse)
        }
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): Flow<ModifyDraftOrderResponse> {
        return flow{
            emit(fakeModifyDraftOrderResponse)
        }
    }

}
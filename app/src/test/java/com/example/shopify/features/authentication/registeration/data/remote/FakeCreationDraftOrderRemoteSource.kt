package com.example.shopify.features.authentication.registeration.data.remote

import com.example.shopify.core.common.data.model.DraftOrder
import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.features.authentication.registration.data.remote.ICreationDraftOrderRemoteSource
import retrofit2.Response

class FakeCreationDraftOrderRemoteSource: ICreationDraftOrderRemoteSource {
    override suspend fun fetchData(vararg params: Any): Response<DraftOrderResponse> {
        val dummyResponse = DraftOrderResponse( DraftOrder(
            id = 1234,
            email = "test@example.com",
            currency = "USD",
            createdAt = "2023-06-25",
            updatedAt = "2023-06-25",
            name = "Draft Order 1",
            status = "Pending",
            lineItems = listOf(/* LineItems */),
            totalPrice = "100.00",
            subtotalPrice = "90.00",
            customer = null,
            appliedDiscount = null,
            orderId = 1,
            tags = "fs",
            paymentTerms = "sdf"
        )
        )
        return Response.success(dummyResponse)
    }
}
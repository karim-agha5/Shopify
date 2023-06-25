package com.example.shopify.features.authentication.registeration.data

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.data.model.DraftOrder
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FakeRegistrationRepository: IRegistrationRepository {
    override fun registerCustomer(customer: CustomerRegistration): Flow<Response<CustomerResponse>> {
        return flow {
            val dummyResponse = CustomerResponse(
                 CustomerResponseInfo(
                    1234,12345,123456,"-","email"
                )
            )
            emit(Response.success(dummyResponse))
        }
    }


    override fun createDraftOrder(draftOrder: DraftOrderRegistration): Flow<Response<DraftOrderResponse>> {
        return flow {
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
            emit(Response.success(dummyResponse))
        }
    }
}
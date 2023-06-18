package com.example.shopify.features.authentication.registration.data

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.DraftOrderResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRegistrationRepository {
    fun registerCustomer(customer: CustomerRegistration): Flow<Response<CustomerResponse>>

    fun createDraftOrder(draftOrder: DraftOrderRegistration): Flow<Response<DraftOrderResponse>>
}
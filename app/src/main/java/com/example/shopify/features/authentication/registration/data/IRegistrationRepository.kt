package com.example.shopify.features.authentication.registration.data

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRegistrationRepository {
    fun registerCustomer(customer: CustomerRegistration): Flow<Response<CustomerResponse>>
}
package com.example.shopify.features.authentication.registration.data.remote.retrofit

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IShopifyRegistrationApi {
    @Headers("X-Shopify-Access-Token: ${Constants.PASSWORD}", "Content-Type: application/json")
    @POST("customers.json")
    suspend fun createCustomer(@Body customer: CustomerRegistration): Response<CustomerResponse>
}
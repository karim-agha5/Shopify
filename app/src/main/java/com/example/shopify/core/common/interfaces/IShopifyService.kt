package com.example.shopify.core.common.interfaces

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IShopifyService {
    @Headers("X-Shopify-Access-Token: ${Constants.password}", "Content-Type: application/json")
    @POST("customers.json")
    suspend fun createCustomer(@Body customer: CustomerRegistration): Response<CustomerResponse>
}
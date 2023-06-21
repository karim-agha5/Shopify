package com.example.shopify.features.orders.network


import com.example.shopify.features.orders.model.model_response.OrderResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface OrderServices {

    @GET("customers/{customerId}/orders.json")
    suspend fun downloadOrdersByCustomerId (@Header("X-Shopify-Access-Token") AccessToken : String, @Path("customerId") customerId: Long) : OrderResponse


//    @Headers("X-Shopify-Access-Token: ${Constants.PASSWORD}", "Content-Type: application/json")
//    @POST("orders.json")
//    suspend fun createOrderWithCustomerId (@Body orderRequest: OrderRequest) : OrderResponse

}
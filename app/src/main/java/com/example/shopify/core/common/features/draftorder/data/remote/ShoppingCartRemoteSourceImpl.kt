package com.example.shopify.core.common.features.draftorder.data.remote

import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateShoppingCartResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.GetShoppingCartResponse
import retrofit2.Retrofit

class ShoppingCartRemoteSourceImpl(
    private val retrofit: Retrofit
) : IShoppingCartRemoteSource {

    private val shoppingCartRemoteService = retrofit.create(ShoppingCartRemoteService::class.java)

    override suspend fun createShoppingCart(
        body: CreateShoppingCartRequestBody
    )
    : CreateShoppingCartResponse {
        return shoppingCartRemoteService.createShoppingCart(body)
    }

    override suspend fun getShoppingCart(draftOrderId: String): GetShoppingCartResponse {
        return shoppingCartRemoteService.getShoppingCart(draftOrderId)
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyShoppingCartRequestBody
    ): GetShoppingCartResponse {
        return shoppingCartRemoteService.modifyShoppingCart(draftOrderId,body)
    }
}
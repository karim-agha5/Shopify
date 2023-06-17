package com.example.shopify.core.common.features.draftorder.data.remote

import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateShoppingCartResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.GetShoppingCartResponse

interface IShoppingCartRemoteSource{
     suspend fun createShoppingCart(body: CreateShoppingCartRequestBody): CreateShoppingCartResponse
     suspend fun getShoppingCart(draftOrderId: String) : GetShoppingCartResponse
     suspend fun modifyShoppingCart(
          draftOrderId: String,
          body: ModifyShoppingCartRequestBody
     ) : GetShoppingCartResponse
}
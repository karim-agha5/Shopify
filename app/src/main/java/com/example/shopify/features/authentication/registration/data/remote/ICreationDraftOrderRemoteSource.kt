package com.example.shopify.features.authentication.registration.data.remote

import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.core.common.interfaces.IMainRemoteDataSource
import retrofit2.Response

interface ICreationDraftOrderRemoteSource: IMainRemoteDataSource<Response<DraftOrderResponse>> {
    override suspend fun fetchData(vararg params: Any): Response<DraftOrderResponse>
}
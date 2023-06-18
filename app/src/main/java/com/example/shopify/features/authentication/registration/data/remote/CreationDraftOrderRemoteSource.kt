package com.example.shopify.features.authentication.registration.data.remote

import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.features.authentication.registration.data.remote.retrofit.IShopifyDraftOrderApi
import retrofit2.Response

class CreationDraftOrderRemoteSource: ICreationDraftOrderRemoteSource {
    override suspend fun fetchData(vararg params: Any): Response<DraftOrderResponse> {
        return RetrofitHelper.getInstance().create(IShopifyDraftOrderApi::class.java).createDraftOrder(params[0] as DraftOrderRegistration)
    }
}
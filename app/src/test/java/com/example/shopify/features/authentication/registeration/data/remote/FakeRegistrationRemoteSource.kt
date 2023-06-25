package com.example.shopify.features.authentication.registeration.data.remote

import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.features.authentication.registration.data.remote.IRegistrationRemoteSource
import retrofit2.Response

class FakeRegistrationRemoteSource: IRegistrationRemoteSource {
    override suspend fun fetchData(vararg params: Any): Response<CustomerResponse> {
        val dummyResponse = CustomerResponse(
            CustomerResponseInfo(
                1234,12345,123456,"-","email"
            )
        )
        return Response.success(dummyResponse)
    }
}
package com.example.shopify.features.authentication.registration.data.remote

import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.interfaces.IMainRemoteDataSource
import retrofit2.Response

interface IRegistrationRemoteSource: IMainRemoteDataSource<Response<CustomerResponse>> {
    override suspend fun fetchData(vararg params: Any): Response<CustomerResponse>

}
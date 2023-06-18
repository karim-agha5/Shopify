package com.example.shopify.features.authentication.registration.data.remote

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.features.authentication.registration.data.remote.retrofit.IShopifyRegistrationApi
import retrofit2.Response

class RegistrationRemoteSource: IRegistrationRemoteSource {
    private val TAG = "RemoteRegistrationRemoteSource"
    override suspend fun fetchData(vararg params: Any): Response<CustomerResponse> {
        return RetrofitHelper.getInstance().create(IShopifyRegistrationApi::class.java).createCustomer(params[0] as CustomerRegistration)
    }
}
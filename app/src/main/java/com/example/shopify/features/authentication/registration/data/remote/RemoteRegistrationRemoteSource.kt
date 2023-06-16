package com.example.shopify.features.authentication.registration.data.remote

import android.util.Log
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.remote.retrofit.API
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper2
import retrofit2.Response

class RemoteRegistrationRemoteSource: IRemoteRegistrationRemoteSource {
    private val TAG = "RemoteRegistrationRemoteSource"
    override suspend fun fetchData(vararg params: Any): Response<CustomerResponse> {
        Log.d(TAG, "fetchData: ${params[0]}")
        return API.retrofitService.createCustomer(params[0] as CustomerRegistration)
    }
}
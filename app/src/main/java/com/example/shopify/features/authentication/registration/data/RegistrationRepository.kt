package com.example.shopify.features.authentication.registration.data

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.features.authentication.registration.data.remote.IRemoteRegistrationRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RegistrationRepository(
    private val remoteSource: IRemoteRegistrationRemoteSource
): IRegistrationRepository {
    override fun registerCustomer(customer: CustomerRegistration): Flow<Response<CustomerResponse>> {
        return flow{
            emit(
                remoteSource.fetchData(customer)
            )
        }
    }
}
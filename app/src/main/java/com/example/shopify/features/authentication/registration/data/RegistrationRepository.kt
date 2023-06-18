package com.example.shopify.features.authentication.registration.data

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.features.authentication.registration.data.remote.ICreationDraftOrderRemoteSource
import com.example.shopify.features.authentication.registration.data.remote.IRegistrationRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RegistrationRepository(
    private val registrationRemoteSource: IRegistrationRemoteSource,
    private val creationDraftRemoteSource: ICreationDraftOrderRemoteSource
): IRegistrationRepository {
    private val TAG = "RegistrationRepository"
    override fun registerCustomer(customer: CustomerRegistration): Flow<Response<CustomerResponse>> {
        return flow{
            emit(
                registrationRemoteSource.fetchData(customer)
            )
        }
    }

    override fun createDraftOrder(draftOrder: DraftOrderRegistration): Flow<Response<DraftOrderResponse>> {
        return flow{
            emit(
                creationDraftRemoteSource.fetchData(draftOrder)
            )
        }
    }
}
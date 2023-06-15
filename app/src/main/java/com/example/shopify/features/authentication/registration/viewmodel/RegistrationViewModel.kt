package com.example.shopify.features.authentication.registration.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerRepo: IRegistrationRepository
): ViewModel() {
    private val TAG = "RegistrationViewModel"
    private var _retrofitStateFlow = MutableStateFlow(ApiState2.Loading as ApiState2<CustomerResponse>)
    var retrofitStateFlow: MutableStateFlow<ApiState2<CustomerResponse>> = _retrofitStateFlow

    fun registerCustomer(customer: CustomerRegistration){
        Log.d(TAG, "registerCustomer: $customer")
        viewModelScope.launch(Dispatchers.IO) {
            registerRepo.registerCustomer(customer).collect{
                if(it.isSuccessful()){
                    Log.d(TAG, "registerCustomer: workkkked  ${it.body()}   ${it.body()?.customer?.firstName}")
                    _retrofitStateFlow.value =ApiState2.Success(it.body()!!)
                }else{
                    Log.d(TAG, "registerCustomer: ---The whole body is null\n${String(it.errorBody()!!.bytes())}")
                    ApiState2.Failure(Throwable(String(it.errorBody()!!.bytes())))
                }
            }
        }
    }
}
package com.example.shopify.features.authentication.registration.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerRepo: IRegistrationRepository,
    private val fragmentActivity: FragmentActivity
): ViewModel() {
    private val TAG = "RegistrationViewModel"
    private val auth: FirebaseAuth
    private var _retrofitStateFlow = MutableStateFlow(ApiState2.Loading as ApiState2<CustomerResponse>)
    var retrofitStateFlow: MutableStateFlow<ApiState2<CustomerResponse>> = _retrofitStateFlow

    init {
        auth = Firebase.auth
    }
    fun registerCustomer(customer: CustomerRegistration, customerPassword: String){
        Log.d(TAG, "registerCustomer: $customer")
        viewModelScope.launch(Dispatchers.IO) {
            registerRepo.registerCustomer(customer).collect{
                Log.d(TAG, "registerCustomer: workkkked  ${it.body()}   ${it.body()?.customer?.firstName}")
                if(it.isSuccessful() && it.code() != 422){
                    registerCustomerFirebase(it.body()!!.customer!!.email!!, customerPassword)
                    _retrofitStateFlow.value =ApiState2.Success(it.body()!!)
                }else{
                    Log.d(TAG, "registerCustomer: ---The whole body is null\n${String(it.errorBody()!!.bytes())}")
                   _retrofitStateFlow.value  = ApiState2.Failure(Throwable(String(it.errorBody()!!.bytes())))
                }
            }
        }
    }

    fun registerCustomerFirebase(
        customerEmail: String,
        customerPassword: String
    ) {
        auth.createUserWithEmailAndPassword(customerEmail, customerPassword)
            .addOnCompleteListener(fragmentActivity) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail: success")
                } else {
                    Log.w(TAG, "createUserWithEmail: failure", task.exception)
                }
            }
    }

}
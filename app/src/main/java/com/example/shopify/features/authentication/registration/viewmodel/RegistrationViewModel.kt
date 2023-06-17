package com.example.shopify.features.authentication.registration.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.DiscountRegistration
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.core.common.data.model.LineItemRegistration
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerRepo: IRegistrationRepository,
    private val fragmentActivity: FragmentActivity
): ViewModel() {
    private val TAG = "RegistrationViewModel"
    private val auth: FirebaseAuth

    private var _customerStateFlow = MutableStateFlow(ApiState2.Loading as ApiState2<CustomerResponse>)
    var customerStateFlow: MutableStateFlow<ApiState2<CustomerResponse>> = _customerStateFlow

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
                    _customerStateFlow.value =ApiState2.Success(it.body()!!)

                    repeat(2){cnt ->
                        createDraftOrder(DraftOrderRegistration(
                            listOf(
                                LineItemRegistration("empty","empty",-1)
                            ),
                            DiscountRegistration("empty","empty","empty","empty","empty"),
                            it.body()!!.customer!!.id!!,
                            true
                        ) , cnt)
                    }
                }else{
                   _customerStateFlow.value  = ApiState2.Failure(Throwable(String(it.errorBody()!!.bytes())))
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

    fun createDraftOrder(draftOrder: DraftOrderRegistration, cnt: Int){
        //cnt if 1 -> first draft (cart) 2 -> second draft (wish list)

        viewModelScope.launch(Dispatchers.IO) {
            registerRepo.createDraftOrder(draftOrder).collect{
                if(it.isSuccessful && it.code() == 200 || it.code() == 201){
                    //TODO this fun is called twice once more for wishlist
                    Log.d(TAG, "createDraftOrder:1 -- ${it.body()}")

                    //TODO to send the id of draft order to firebase
                }else{
                    Log.d(TAG, "createDraftOrder:2 -- ${String(it.errorBody()!!.bytes())}")
                    //TODO to send the id of draft order to firebase with -1 ex:
                }
            }
        }
    }
}
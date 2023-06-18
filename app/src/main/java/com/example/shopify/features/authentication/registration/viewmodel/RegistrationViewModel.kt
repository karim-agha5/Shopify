package com.example.shopify.features.authentication.registration.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.CustomerFirebase
import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.data.model.DiscountRegistration
import com.example.shopify.core.common.data.model.DraftOrderBody
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.LineItemRegistration
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.authentication.registration.data.IRegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerRepo: IRegistrationRepository,
    private val fragmentActivity: FragmentActivity
) : ViewModel() {
    private val TAG = "RegistrationViewModel"
    private val auth: FirebaseAuth
    private var database: DatabaseReference
    private var dbRef: DatabaseReference
    private val dbPath = "customers/emails/"

    private var _customerStateFlow =
        MutableStateFlow(ApiState2.Loading as ApiState2<CustomerResponse>)
    var customerStateFlow: MutableStateFlow<ApiState2<CustomerResponse>> = _customerStateFlow

    init {
        auth = Firebase.auth
        database = Firebase.database.reference
        dbRef = database.ref.child(dbPath)
    }

    fun registerCustomer(customer: CustomerRegistration, customerPassword: String) {
        Log.d(TAG, "registerCustomer: $customer")
        viewModelScope.launch(Dispatchers.IO) {
            registerRepo.registerCustomer(customer).collect {
                Log.d(
                    TAG,
                    "registerCustomer: workkkked  ${it.body()}   ${it.body()?.customer?.firstName}"
                )
                if (it.isSuccessful() && it.code() != 422) {
                    authenticateCustomerFirebase(it.body()!!.customer!!.email!!, customerPassword)
                    _customerStateFlow.value = ApiState2.Success(it.body()!!)

                    createDraftOrder(
                        DraftOrderRegistration(
                            DraftOrderBody(
                                listOf(
                                    LineItemRegistration("empty", "00.00", 1)
                                ),
                                DiscountRegistration("empty", "empty", "00.00", "00.00", "empty"),
                                CustomerId(it.body()!!.customer!!.id!!),
                                true
                            )
                        ), it.body()!!.customer!!
                    )
                } else {
                    _customerStateFlow.value =
                        ApiState2.Failure(Throwable(String(it.errorBody()!!.bytes())))
                }
            }
        }
    }

    fun authenticateCustomerFirebase(
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

    fun appendCustomerFirebase(
        id: Long,
        cartId: Long,
        wishListId: Long,
        name: String,
        email: String,
        coupon: String
    ) {
        val key = dbRef.push().key
        key?.let { dbRef.child(it).setValue(CustomerFirebase(name,email,id,cartId,wishListId,coupon)).addOnCompleteListener {
            Log.d(TAG, "appendCustomerFirebase: ---complete")
            }.addOnCanceledListener {
            Log.d(TAG, "appendCustomerFirebase: ---no")
        }
        }
        /*database.child("$dbPath$email/customer_id").setValue(id)
        database.child("$dbPath$email/cart_id").setValue(cartId)
        appendWishListIdFirebase(email, wishListId)
        database.child("$dbPath$email/name").setValue(name)
        database.child("$dbPath$email/coupon").setValue(coupon)*/
    }

    fun createDraftOrder(
        draftOrder: DraftOrderRegistration,
        customerInfo: CustomerResponseInfo
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            registerRepo.createDraftOrder(draftOrder).collect {
                if (it.isSuccessful && it.code() == 200 || it.code() == 201) {
                    Log.d(TAG, "createDraftOrder:1 -- ${it.body()}")

                    appendCustomerFirebase(
                        customerInfo.id!!,
                        1127634796864+1,
                        1127634796864,
                        customerInfo.firstName!!,
                        customerInfo.email!!.replace(".", "|"),
                        "-"
                    )

                        /*val childUpdate: MutableMap<String, Any> = HashMap()
                        childUpdate[
                                "customers/emails/${customerInfo.email!!.replace(".","|")}/wish_list_id"
                        ] = it.body()!!.draftOrder!!.id!!

                        database.updateChildren(childUpdate).addOnCompleteListener {
                            Log.d(TAG, "updateChildren: ${it.isComplete}")
                        }*/
                } else {
                    Log.d(TAG, "createDraftOrder:2 -- ${String(it.errorBody()!!.bytes())}")
                    //TODO to send the id of draft order to firebase with -1 ex:
                }
            }
        }
    }
}
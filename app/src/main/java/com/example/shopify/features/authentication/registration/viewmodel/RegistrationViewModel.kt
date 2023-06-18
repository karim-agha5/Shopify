package com.example.shopify.features.authentication.registration.viewmodel

import android.app.Activity
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
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.Discount
import com.example.shopify.core.common.features.draftorder.model.RequestCustomer
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestLineItem
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.MainActivity
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
    private val draftOrderRepo: IDraftOrderRepository,
    private val activity: Activity
) : ViewModel() {
    private val TAG = "RegistrationViewModel"
    private val auth: FirebaseAuth
    private var database: DatabaseReference
    private var dbRef: DatabaseReference
    private val dbPath = "customers/emails/"
    private lateinit var customerInfo: CustomerResponseInfo

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

                    Log.w(TAG, "---be1---appendCustomerFirebase:-----hiiii")

                    customerInfo = it.body()!!.customer!!
                    Log.w(TAG, "---be2---appendCustomerFirebase:-----hiiii")

                    customerInfo.cartId = createDraftOrder(CreateDraftOrderRequestBody(
                        CreateDraftOrderRequestDraftOrder(
                            listOf(CreateDraftOrderRequestLineItem("dummy","00.00",1)),
                            Discount(description = "dummy" , value = "00.00", amount = "00.00", title = "dummy"),
                            RequestCustomer(it.body()!!.customer!!.id!!)
                        )
                    ))
                    Log.w(TAG, "---be3---appendCustomerFirebase:-----hiiii")
                    customerInfo.wishListId = createDraftOrder(CreateDraftOrderRequestBody(
                        CreateDraftOrderRequestDraftOrder(
                            listOf(CreateDraftOrderRequestLineItem("dummy","00.00",1)),
                            Discount(description = "dummy" , value = "00.00", amount = "00.00", title = "dummy"),
                            RequestCustomer(it.body()!!.customer!!.id!!)
                        )
                    ))

                    Log.w(TAG, "---be4---appendCustomerFirebase:-----hiiii")
                    appendCustomerFirebase(
                        it.body()!!.customer!!.id!!,
                        customerInfo.cartId!!,
                        customerInfo.wishListId!!,
                        it.body()!!.customer!!.firstName!!,
                        it.body()!!.customer!!.email!!,
                        "-"
                    )

                    _customerStateFlow.value = ApiState2.Success(it.body()!!)
                } else {
                    Log.w(TAG, "---be5---appendCustomerFirebase:-----hiiii")
                    _customerStateFlow.value =
                        ApiState2.Failure(Throwable(String(it.errorBody()!!.bytes())))
                }
            }
        }
    }

    private fun authenticateCustomerFirebase(
        customerEmail: String,
        customerPassword: String
    ) {
        auth.createUserWithEmailAndPassword(customerEmail, customerPassword)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail: success")
                } else {
                    Log.w(TAG, "createUserWithEmail: failure", task.exception)
                }
            }
    }

    private fun appendCustomerFirebase(
        id: Long,
        cartId: Long,
        wishListId: Long,
        name: String,
        email: String,
        coupon: String
    ) {
        Log.w(TAG, "appendCustomerFirebase:-----hiiii")
        customerInfo.cartId = cartId
        customerInfo.wishListId = wishListId
        customerInfo.coupon = "-"

        (activity as MainActivity).customerInfo = customerInfo

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

    private suspend fun createDraftOrder(
        draftOrderBody: CreateDraftOrderRequestBody
    ): Long {
         return draftOrderRepo.createShoppingCart(draftOrderBody).draftOrder.id!!
/*
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

                        val childUpdate: MutableMap<String, Any> = HashMap()
                        childUpdate[
                                "customers/emails/${customerInfo.email!!.replace(".","|")}/wish_list_id"
                        ] = it.body()!!.draftOrder!!.id!!

                        database.updateChildren(childUpdate).addOnCompleteListener {
                            Log.d(TAG, "updateChildren: ${it.isComplete}")
                        }
                } else {
                    Log.d(TAG, "createDraftOrder:2 -- ${String(it.errorBody()!!.bytes())}")
                    //TODO to send the id of draft order to firebase with -1 ex:
                }
            }
        }
*/
    }
}
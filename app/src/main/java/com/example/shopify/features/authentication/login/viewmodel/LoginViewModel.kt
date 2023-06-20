package com.example.shopify.features.authentication.login.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginViewModel(private val fragmentActivity: FragmentActivity) : ViewModel() {
    private val TAG = "LoginViewModel"
    private var auth: FirebaseAuth
    private var database: DatabaseReference

    init {
        auth = Firebase.auth
        database = Firebase.database.reference
    }

    fun loginCustomerFirebase(
        customerEmail: String,
        customerPassword: String,
        callback: (CustomerResponseInfo?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(customerEmail, customerPassword)
            .addOnCompleteListener(fragmentActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
                    getCustomerByEmail(customerEmail){
                        callback(it!!)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    callback(null)
                }
            }
    }

    fun getCustomerByEmail(email: String, callback: (CustomerResponseInfo?) -> Unit) {
        val emailRef = database.child("customers/emails/")
        val query = emailRef.orderByKey()

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val snapshotIterator = dataSnapshot.children
                val iterator: Iterator<DataSnapshot> = snapshotIterator.iterator()

                while (iterator.hasNext()) {
                    val snapshot = iterator.next()
                    val customerEmail = snapshot.child("email").getValue(String::class.java)?.replace("|", ".")
                    if (customerEmail == email) {
                        Log.i(TAG, "Firebase found Value")

                        val customerId = snapshot.child("customerId").getValue(Long::class.java)
                        val cartId = snapshot.child("cartId").getValue(Long::class.java)
                        val wishListId = snapshot.child("wishListId").getValue(Long::class.java)
                        val coupon = snapshot.child("coupon").getValue(String::class.java)

                        callback(CustomerResponseInfo(customerId, cartId, wishListId, coupon, email))
                        break
                    } else {
                        Log.i(TAG, "Customer not found for email: $email")


                        //TODO to handle because above is used !!

                    }
                }
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error case
                Log.e(TAG, "Error retrieving customer: ${databaseError.message}")
            }
        })
    }

}
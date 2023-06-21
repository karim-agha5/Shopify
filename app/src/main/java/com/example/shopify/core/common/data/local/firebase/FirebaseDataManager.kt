package com.example.shopify.core.common.data.local.firebase

import android.util.Log
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseDataManager {
    private val TAG = "FirebaseDataManager"
    private var database: DatabaseReference

    init {
        database = Firebase.database.reference
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
                        val customerName = snapshot.child("name").getValue(String::class.java)

                        callback(CustomerResponseInfo(customerId, cartId, wishListId, coupon, email, firstName = customerName))
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
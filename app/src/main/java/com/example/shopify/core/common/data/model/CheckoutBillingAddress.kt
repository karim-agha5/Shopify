package com.example.shopify.core.common.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckoutBillingAddress(
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val address1: String?,
    val phone: String?,
    val city: String?,
    val country: String?
) : Parcelable

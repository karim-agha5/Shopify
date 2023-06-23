package com.example.shopify.core.common.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckoutShippingLines(
    val title: String?,
    val price: Double,
    val code: String,
) : Parcelable

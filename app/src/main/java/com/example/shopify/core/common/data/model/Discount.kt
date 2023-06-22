package com.example.shopify.core.common.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Discount(
    val description: String?,
    val title: String?,
    @SerializedName("value_type")
    val valueType: String?,
    val value: Int?,
    val amount: String?
) : Parcelable

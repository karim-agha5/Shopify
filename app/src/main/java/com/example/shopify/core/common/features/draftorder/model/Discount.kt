package com.example.shopify.core.common.features.draftorder.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Discount(
    val description: String?,
    @SerializedName("value_type")
    val valueType: String = "fixed_amount",
    val value: String?,
    val amount: String?,
    val title: String?
) : Parcelable
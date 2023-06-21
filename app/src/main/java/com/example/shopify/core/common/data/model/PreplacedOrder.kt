package com.example.shopify.core.common.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreplacedOrder(
    @SerializedName("variant_id")
     val variantId: Long?,
    @SerializedName("product_id")
    val productId: Long?,
    val title: String?,
    @SerializedName("variant_title")
    val variantTitle: String?,
    val quantity: Int?,
    //@SerializedName("applied_discount")
    //val appliedDiscount: com.example.shopify.core.common.features.draftorder.model.Discount?,
    val name: String?,
    val price: String?
) : Parcelable

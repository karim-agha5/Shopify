package com.example.shopify.core.common.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long?,
    var selectedVariantIndex: Long? = 0,
    val image: ImageX?,
    val images: List<ImageX>,
    val options: List<Option>,
    val product_type: String?,
    val status: String?,
    val tags: String?,
    val title: String?,
    val variants: List<Variant>,
    val vendor: String?,
    var isFav : Boolean? = false,
    var rating : String?
): Parcelable
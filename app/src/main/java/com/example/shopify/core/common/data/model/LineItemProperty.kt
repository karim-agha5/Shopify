package com.example.shopify.core.common.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LineItemProperty(
    val name: String?,
    val value: String?
) : Parcelable

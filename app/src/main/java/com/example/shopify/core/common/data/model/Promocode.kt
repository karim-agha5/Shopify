package com.example.shopify.core.common.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Promocode(
    var code: String = "Unknown",
    var percentage: String = "Unknown"
): Parcelable

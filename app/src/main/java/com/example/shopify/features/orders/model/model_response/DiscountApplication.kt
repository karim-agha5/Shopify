package com.example.shopify.features.orders.model.model_response

import android.os.Parcel
import android.os.Parcelable

data class DiscountApplication(
    val target_type: String?,
    val type: String?,
    val value: String?,
    val value_type: String?,
    val allocation_method: String?,
    val target_selection: String?,
    val title: String?,
    val description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(target_type)
        parcel.writeString(type)
        parcel.writeString(value)
        parcel.writeString(value_type)
        parcel.writeString(allocation_method)
        parcel.writeString(target_selection)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiscountApplication> {
        override fun createFromParcel(parcel: Parcel): DiscountApplication {
            return DiscountApplication(parcel)
        }

        override fun newArray(size: Int): Array<DiscountApplication?> {
            return arrayOfNulls(size)
        }
    }
}
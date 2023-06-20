package com.example.shopify.features.orders.model.model_response

import android.os.Parcel
import android.os.Parcelable

data class DiscountCode(
    val amount: String?,
    val code: String?,
    val type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(amount)
        parcel.writeString(code)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiscountCode> {
        override fun createFromParcel(parcel: Parcel): DiscountCode {
            return DiscountCode(parcel)
        }

        override fun newArray(size: Int): Array<DiscountCode?> {
            return arrayOfNulls(size)
        }
    }
}
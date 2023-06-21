package com.example.shopify.features.orders.model.model_response

import android.os.Parcel
import android.os.Parcelable

data class OrderCustomer(
    val created_at: String?,
    val currency: String?,
    val default_address: DefaultAddress,
    val email: String?,
    val first_name: String?,
    val id: Long,
    val last_name: String?,
    val phone: String?,
    val updated_at: String?,
    val verified_email: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        created_at = parcel.readString(),
        currency = parcel.readString(),
        default_address = parcel.readParcelable(DefaultAddress::class.java.classLoader)!!,
        email = parcel.readString(),
        first_name = parcel.readString(),
        id = parcel.readLong(),
        last_name = parcel.readString(),
        phone = parcel.readString(),
        updated_at = parcel.readString(),
        verified_email = parcel.readByte() != 0.toByte()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(created_at)
        parcel.writeString(currency)
        parcel.writeString(email)
        parcel.writeString(first_name)
        parcel.writeLong(id)
        parcel.writeString(last_name)
        parcel.writeString(phone)
        parcel.writeString(updated_at)
        parcel.writeByte(if (verified_email) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderCustomer> {
        override fun createFromParcel(parcel: Parcel): OrderCustomer {
            return OrderCustomer(parcel)
        }

        override fun newArray(size: Int): Array<OrderCustomer?> {
            return arrayOfNulls(size)
        }
    }
}
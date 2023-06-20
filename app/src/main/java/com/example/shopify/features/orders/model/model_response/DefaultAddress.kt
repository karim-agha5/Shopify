package com.example.shopify.features.orders.model.model_response

import android.os.Parcel
import android.os.Parcelable

data class DefaultAddress(
    val address1: String?,
    val city: String?,
    val country: String?,
    val country_code: String?,
    val country_name: String?,
    val customer_id: Long,
    val default: Boolean,
    val first_name: String?,
    val id: Long,
    val last_name: String?,
    val name: String?,
    val phone: String?,
    val province: String?,
    val province_code: String?,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        address1 = parcel.readString(),
        city = parcel.readString(),
        country = parcel.readString(),
        country_code = parcel.readString(),
        country_name = parcel.readString(),
        customer_id = parcel.readLong(),
        default = parcel.readByte() != 0.toByte(),
        first_name = parcel.readString(),
        id = parcel.readLong(),
        last_name = parcel.readString(),
        name = parcel.readString(),
        phone = parcel.readString(),
        province = parcel.readString(),
        province_code = parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address1)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeString(country_code)
        parcel.writeString(country_name)
        parcel.writeLong(customer_id)
        parcel.writeByte(if (default) 1 else 0)
        parcel.writeString(first_name)
        parcel.writeLong(id)
        parcel.writeString(last_name)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(province)
        parcel.writeString(province_code)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DefaultAddress> {
        override fun createFromParcel(parcel: Parcel): DefaultAddress {
            return DefaultAddress(parcel)
        }

        override fun newArray(size: Int): Array<DefaultAddress?> {
            return arrayOfNulls(size)
        }
    }
}
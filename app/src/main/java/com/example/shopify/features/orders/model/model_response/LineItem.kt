package com.example.shopify.features.orders.model.model_response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LineItem(
    //val duties: List<Any>?,
    val fulfillable_quantity: Int?,
    val fulfillment_service: String?,
    //val fulfillment_status: Any?,
    val gift_card: Boolean?,
    val grams: Int?,
    val id: Long?,
    val name: String?,
    val price: String?,
    val product_exists: Boolean?,
    val product_id: Long?,
    val properties: List<LinkedTreeMap<String,String>>?,
    val quantity: Int?,
    val requires_shipping: Boolean?,
    val sku: String?,
    //val tax_lines: List<Any>?,
    val taxable: Boolean?,
    val title: String?,
    val total_discount: String?,
    val variant_id: Long?,
    val variant_inventory_management: String?,
    val variant_title: String?,
    val vendor: String?,
) : Parcelable /*{
    constructor(parcel: Parcel) : this(
        parcel.createListOfAny(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readValue(Any::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.createListOfAny(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.createListOfAny(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(fulfillable_quantity)
        parcel.writeString(fulfillment_service)
        parcel.writeByte(if (gift_card) 1 else 0)
        parcel.writeInt(grams)
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeByte(if (product_exists) 1 else 0)
        parcel.writeLong(product_id)
        parcel.writeInt(quantity)
        parcel.writeByte(if (requires_shipping) 1 else 0)
        parcel.writeString(sku)
        parcel.writeByte(if (taxable) 1 else 0)
        parcel.writeString(title)
        parcel.writeString(total_discount)
        parcel.writeLong(variant_id)
        parcel.writeString(variant_inventory_management)
        parcel.writeString(variant_title)
        parcel.writeString(vendor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LineItem> {
        override fun createFromParcel(parcel: Parcel): LineItem {
            return LineItem(parcel)
        }

        override fun newArray(size: Int): Array<LineItem?> {
            return arrayOfNulls(size)
        }
    }
}
private fun Parcel.createListOfAny(): List<Any> {
    val size = readInt()
    val list = mutableListOf<Any>()
    repeat(size) {
        list.add(readValue(Any::class.java.classLoader)!!)
    }
    return list
}*/
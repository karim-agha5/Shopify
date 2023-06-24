package com.example.shopify.core.common.mappers

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.features.draftorder.model.Discount
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestLineItem
import com.google.gson.internal.LinkedTreeMap

class ProductMapper {
    companion object{
        fun convertProductToLineItem(product: Product): ModifyDraftOrderRequestLineItem {
            val linkedTreeMap = LinkedTreeMap<String,String>()
            linkedTreeMap["name"] = product.image?.src
            linkedTreeMap["value"] = product.variants[product.selectedVariantIndex!!.toInt()].inventory_quantity.toString()
            return ModifyDraftOrderRequestLineItem(
                null,
                product.variants[product.selectedVariantIndex!!.toInt()].id,
                product.id,
                product.title,
                product.variants[product.selectedVariantIndex!!.toInt()].title,
                //product.variants[product.selectedVariantIndex!!.toInt()].inventory_quantity,
                1,
                null,
                appliedDiscount = Discount("initial", value = "00.00", amount = "00.00", title = "initial"),
                properties = listOf(linkedTreeMap),
                price = product.variants[product.selectedVariantIndex!!.toInt()].price.toString()
            )
        }
    }
}
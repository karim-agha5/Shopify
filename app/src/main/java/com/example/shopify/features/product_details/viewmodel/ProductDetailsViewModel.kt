package com.example.shopify.features.product_details.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.LineItemProperty
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDiscount
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestLineItem
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.common.mappers.LineItemsMapper
import com.example.shopify.core.common.mappers.ProductMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsViewModel(private val draftOrderId: Long?, private val draftRepo: IDraftOrderRepository): ViewModel() {
    private val TAG = "ProductDetailsViewModel"
    private var auth: FirebaseAuth
    private var lineItemsResponse: MutableList<ModifyDraftOrderRequestLineItem>? = null
    private var productCtr = "1"

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun addToCart(product: Product,quantity: Int, callback: (Boolean) -> Unit){
        Log.d(TAG, "addToCart: start1")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "addToCart: start2")
            draftRepo.getShoppingCart(draftOrderId.toString()).collectLatest {
                Log.d(TAG, "addToCart: ++first call done++ ${it.draftOrder.lineItems?.size}")


                lineItemsResponse = LineItemsMapper.fromResponseToRequestLineItems(it.draftOrder.lineItems!!).toMutableList()

                //assign image link and quantity to use in the checkout screen
               /* lineItemsResponse!![lineItemsResponse!!.size-1].properties = listOf(
                    LineItemProperty("image",product.image.src),
                    LineItemProperty("quantity",productCtr)
                )*/

                //converting product to lineItem and add it to list
                //lineItemsResponse?.add(ProductMapper.convertProductToLineItem(product))

                /*
                * Map a product to a line item and then copy the line item object with the modified
                * quantity that was specified by the user in the product details.
                * TODO this is a work around. The quantity should be set in the product details fragment.
                *  Fix later.
                * */

                val lineItem = ProductMapper.convertProductToLineItem(product).copy(quantity = quantity)
                lineItemsResponse?.add(lineItem)
                Log.d(TAG, "addToCart: ${ProductMapper.convertProductToLineItem(product)}")

                //send put request
                draftRepo.modifyShoppingCart(draftOrderId.toString(), ModifyDraftOrderRequestBody(
                    ModifyDraftOrderRequestDraftOrder(
                        "cart",
                        auth.currentUser?.email!!,
                        true,
                        lineItemsResponse!!,
                        ModifyDraftOrderRequestDiscount("initial","00.00","initial","00.00")
                    )
                )).collectLatest {
                    Log.d(TAG, "addToCart: ++SSecond Success++ ${it.draftOrder.lineItems?.size}")
                    withContext(Dispatchers.Main){
                        callback(true)
                    }
                }
            }
        }
    }

    fun setProductCounter(ctr: String){
        productCtr = ctr
    }
}
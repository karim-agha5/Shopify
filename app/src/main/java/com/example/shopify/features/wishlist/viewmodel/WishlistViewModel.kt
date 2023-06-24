package com.example.shopify.features.wishlist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDiscount
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestLineItem
import com.example.shopify.core.common.mappers.LineItemsMapper
import com.example.shopify.core.common.mappers.ProductMapper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishlistViewModel(private val draftOrderId: Long?, private val draftRepo: IDraftOrderRepository): ViewModel() {
    private val TAG = "wishlistViewModel"
    private var auth: FirebaseAuth
    private var lineItemsResponse: MutableList<ModifyDraftOrderRequestLineItem>? = null

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun addToWishList(product: Product, callback: (Boolean) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "addToWishList: start")
            draftRepo.getShoppingCart(draftOrderId.toString()).collectLatest {
                Log.d(TAG, "addToWishList: ++first call done++ ${it.draftOrder.lineItems?.size}")


                lineItemsResponse = LineItemsMapper.fromResponseToRequestLineItems(it.draftOrder.lineItems!!).toMutableList()

                val lineItem = ProductMapper.convertProductToLineItem(product)

                if(lineItemsResponse?.contains(lineItem) == false){
                    lineItemsResponse?.add(lineItem)
                }

                Log.d(TAG, "addToWishList: ${ProductMapper.convertProductToLineItem(product)}")

                //send put request
                draftRepo.modifyShoppingCart(draftOrderId.toString(), ModifyDraftOrderRequestBody(
                    ModifyDraftOrderRequestDraftOrder(
                        "wishlist",
                        auth.currentUser?.email!!,
                        true,
                        lineItemsResponse!!,
                        ModifyDraftOrderRequestDiscount("initial","00.00","initial","00.00")
                    )
                )
                ).collectLatest {
                    Log.d(TAG, "addToCart: ++SSecond Success++ ${it.draftOrder.lineItems?.size}")
                    withContext(Dispatchers.Main){
                        callback(true)
                    }
                }
            }
        }
    }

    fun getWishList(){
        viewModelScope.launch(Dispatchers.IO) {
            draftRepo.getShoppingCart(draftOrderId.toString()).collectLatest {
                
            }
        }
    }
}
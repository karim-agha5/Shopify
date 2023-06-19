package com.example.shopify.core.common.mappers

import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestLineItem
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem

class LineItemsMapper {

   companion object{
       fun fromResponseToRequestLineItems(
           orders: List<ModifyDraftOrderResponseLineItem>
       )
               : List<ModifyDraftOrderRequestLineItem>{
           val requestOrders = mutableListOf<ModifyDraftOrderRequestLineItem>()
           var lineItem: ModifyDraftOrderRequestLineItem
           for (order in orders){
               lineItem = ModifyDraftOrderRequestLineItem(
                   order.id,
                   order.variantId,
                   order.productId,
                   order.title,
                   order.variantTitle,
                   order.requestedQuantity,
                   order.requiresShipping,
                   order.fulfillmentService,
                   order.appliedDiscount,
                   order.properties,
                   order.price
               )
               requestOrders.add(lineItem)
           }
           return requestOrders
       }
   }

}
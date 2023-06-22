package com.example.shopify.features.shoppingcart.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.databinding.OrderItemLayoutBinding
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.util.getVariantOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class OrderItemsAdapter(
    private var ordersList: MutableList<ModifyDraftOrderResponseLineItem>,
    private val cartOrderItemHandler: CartOrderItemHandler,
    private val totalAmountHandler: TotalAmountHandler,
    private val context: Context
) : RecyclerView.Adapter<OrderItemsAdapter.OrderItemViewHolder>() {

    lateinit var binding: OrderItemLayoutBinding

    inner class OrderItemViewHolder(val binding: OrderItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var calcPosition = 0

        init {
            /*
             * TODO fix the increment and decrement buttons being clicked more than one time
             *  immediately fast as it causes the cart to duplicate items
             * */

            binding.btnOrderItemDecrement.setOnClickListener {
                if (binding.tvNumberOfOrderItems.text.toString().toInt() >= 0) {

                    if (binding.tvNumberOfOrderItems.text.toString().toInt() == 1) {

                        // TODO Remove the item from the draft order in the API
                        showDeletionDialog(calcPosition, false)
                    } else {
                        cartOrderItemHandler.decrementOrder(ordersList[calcPosition], calcPosition + 1)
                        binding.tvNumberOfOrderItems.text =
                            "${binding.tvNumberOfOrderItems.text.toString().toInt() - 1}"
                        adjustTotalAmountValue(ordersList[calcPosition])
                    }

                }

            }

            binding.btnOrderItemIncrement.setOnClickListener {
                /* if(
                     binding.tvNumberOfOrderItems.text.toString().toInt()
                     < (ordersList[calcPosition].requestedQuantity ?: 0)
                 ) {*/

                cartOrderItemHandler.incrementOrder(ordersList[calcPosition], calcPosition + 1)
                binding.tvNumberOfOrderItems.text =
                    "${binding.tvNumberOfOrderItems.text.toString().toInt() + 1}"

                totalAmountHandler.adjustPrice(ordersList[calcPosition].price?.toDouble())
                // }
            }

            binding.btnOrderItemMoreVert.setOnClickListener {
                val menu = PopupMenu(context, it)
                menu.menuInflater.inflate(R.menu.order_item_menu, menu.menu)

                menu.setOnMenuItemClickListener {
                    showDeletionDialog(calcPosition, true)
                    true
                }
                menu.show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.order_item_layout,
            parent,
            false
        )
        return OrderItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        binding.orderItemLoading.visibility = View.GONE
        holder.binding.tvOrderItemName.text = ordersList[position].title
        val options = getVariantOptions(ordersList[position].variantTitle)
        holder.binding.tvColorValue.text = options.second
        holder.binding.tvSizeValue.text = options.first
        holder.binding.tvOrderItemPrice.text = ordersList[position].price
        holder.binding.tvNumberOfOrderItems.text = ordersList[position].requestedQuantity.toString()
        holder.calcPosition = position

    }

    override fun getItemCount(): Int {
        return ordersList.size
    }


    fun submitList(orders: MutableList<ModifyDraftOrderResponseLineItem>?) {
        ordersList.clear()
        ordersList = orders?.toMutableList() ?: mutableListOf()
        ordersList.removeAt(0)
    }

    private fun adjustTotalAmountValue(order: ModifyDraftOrderResponseLineItem) {
        totalAmountHandler.adjustPrice(
            order.price?.toDouble()
                ?.times(-1)
        )
    }

    private fun adjustTotalAmountWhenDeleted(order: ModifyDraftOrderResponseLineItem) {
        totalAmountHandler.adjustPrice(
            order.price?.toDouble()
                ?.times(-1)?.times(binding.tvNumberOfOrderItems.text.toString().toInt())
        )
    }

    private fun showDeletionDialog(calcPosition: Int, isDeleteAllOrdersTapped: Boolean) {
        MaterialAlertDialogBuilder(context, R.style.MyDialogTheme)
            .setTitle(R.string.deletion_dialog_title)
            .setMessage(R.string.deletion_dialog_message)
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.delete) { dialog, _ ->
                if (isDeleteAllOrdersTapped) {
                    adjustTotalAmountWhenDeleted(ordersList[calcPosition])
                } else {
                    adjustTotalAmountValue(ordersList[calcPosition])
                }
                cartOrderItemHandler.removeOrder(ordersList[calcPosition])
                dialog.dismiss()
            }
            .show()
        // TODO Adjust the price
    }

}

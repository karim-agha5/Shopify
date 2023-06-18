package com.example.shopify.features.shoppingcart.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.databinding.OrderItemLayoutBinding
import com.example.shopify.core.common.features.draftorder.model.Order
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OrderItemsAdapter(
    private var ordersList: MutableList<Order>,
    private val cartOrderItemHandler: CartOrderItemHandler,
    private val totalAmountHandler: TotalAmountHandler,
    private val context: Context
) : RecyclerView.Adapter<OrderItemsAdapter.OrderItemViewHolder>() {

    private lateinit var binding: OrderItemLayoutBinding

    inner class OrderItemViewHolder(val binding: OrderItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var calcPosition = 0

        init {
            binding.btnOrderItemDecrement.setOnClickListener {
                if(binding.tvNumberOfOrderItems.text.toString().toInt() >=  0) {

                    if(binding.tvNumberOfOrderItems.text.toString().toInt() == 1){

                        adjustTotalAmountValue(ordersList[calcPosition])
                        // TODO Remove the item from the draft order in the API
                        /*cartOrderItemHandler.removeOrder(ordersList[calcPosition])
                        ordersList.remove(ordersList[calcPosition])
                        notifyDataSetChanged()*/
                        showDeletionDialog(calcPosition)
                    }

                    else{
                        binding.tvNumberOfOrderItems.text =
                            "${binding.tvNumberOfOrderItems.text.toString().toInt() - 1}"
                        adjustTotalAmountValue(ordersList[calcPosition])

                    }

                }

            }

            binding.btnOrderItemIncrement.setOnClickListener {
                if(
                    binding.tvNumberOfOrderItems.text.toString().toInt()
                    < (ordersList[calcPosition].orderItemQuantity?.toInt() ?: 0)
                ) {

                    binding.tvNumberOfOrderItems.text =
                        "${binding.tvNumberOfOrderItems.text.toString().toInt() + 1}"

                    totalAmountHandler.adjustPrice(ordersList[calcPosition].orderItemPrice?.toDouble())
                }
            }

            binding.btnOrderItemMoreVert.setOnClickListener {
                val menu = PopupMenu(context,it)
                menu.menuInflater.inflate(R.menu.order_item_menu,menu.menu)

                menu.setOnMenuItemClickListener {
                    showDeletionDialog(calcPosition)
                    true
                }
                menu.show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.order_item_layout,parent,false)
        return OrderItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.binding.tvOrderItemName.text = ordersList[position].orderItemName
        holder.binding.tvColorValue.text = ordersList[position].orderItemColor
        holder.binding.tvSizeValue.text = ordersList[position].orderItemSize
        holder.binding.tvOrderItemPrice.text = ordersList[position].orderItemPrice
        holder.calcPosition = position
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun submitList(orders: MutableList<Order>){
        this.ordersList = orders
    }

    private fun adjustTotalAmountValue(order: Order){
        totalAmountHandler.adjustPrice(
            order.orderItemPrice?.toDouble()
                ?.times(-1)
        )
    }

    private fun showDeletionDialog(calcPosition: Int){
        MaterialAlertDialogBuilder(context,R.style.MyDialogTheme)
            .setTitle(R.string.deletion_dialog_title)
            .setMessage(R.string.deletion_dialog_message)
            .setNegativeButton(R.string.cancel){ dialog,_->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.delete){dialog,_->
                cartOrderItemHandler.removeOrder(ordersList[calcPosition])
                ordersList.remove(ordersList[calcPosition])
                notifyDataSetChanged()
                dialog.dismiss()
            }
            .show()
        // TODO Adjust the price
    }
}
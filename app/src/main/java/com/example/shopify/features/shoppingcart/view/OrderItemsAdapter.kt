package com.example.shopify.features.shoppingcart.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.databinding.OrderItemLayoutBinding
import com.example.shopify.core.common.features.draftorder.model.Order

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
                        cartOrderItemHandler.removeOrder(ordersList[calcPosition])
                        ordersList.remove(ordersList[calcPosition])
                        notifyDataSetChanged()

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
                Toast.makeText(context, "More Vert", Toast.LENGTH_SHORT).show()
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
}
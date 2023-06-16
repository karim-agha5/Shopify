package com.example.shopify.features.shoppingcart.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.features.shoppingcart.model.Order
import com.google.android.material.button.MaterialButton

class OrderItemsAdapter(
    private val ordersList: List<Order>,
    private val context: Context
) : RecyclerView.Adapter<OrderItemsAdapter.OrderItemViewHolder>() {

    inner class OrderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivOrderItemImage: ImageView = itemView.findViewById(R.id.iv_order_item_image)
        val tvOrderItemName: TextView = itemView.findViewById(R.id.tv_order_item_name)
        val tvOrderColorValue: TextView = itemView.findViewById(R.id.tv_color_value)
        val tvOrderSizeValue: TextView = itemView.findViewById(R.id.tv_size_value)
        val tvOrderItemPrice: TextView = itemView.findViewById(R.id.tv_order_item_price)
        val tvNumberOfOrderItems: TextView = itemView.findViewById(R.id.tv_number_of_order_items)
        val btnDecrement: MaterialButton = itemView.findViewById(R.id.btn_order_item_decrement)
        val btnIncrement: MaterialButton = itemView.findViewById(R.id.btn_order_item_increment)
        val btnMoreVert: ImageButton = itemView.findViewById(R.id.btn_order_item_more_vert)

        init {
            btnDecrement.setOnClickListener {
                Toast.makeText(context, "Decrement", Toast.LENGTH_SHORT).show()
            }

            btnIncrement.setOnClickListener {
                Toast.makeText(context, "Increment", Toast.LENGTH_SHORT).show()
            }

            btnMoreVert.setOnClickListener {
                Toast.makeText(context, "More Vert", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item_layout,parent,false)
        return OrderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.tvOrderItemName.text = ordersList[position].orderItemName
        holder.tvOrderColorValue.text = ordersList[position].orderItemColor
        holder.tvOrderSizeValue.text = ordersList[position].orderItemSize
        holder.tvNumberOfOrderItems.text = "2"
        holder.tvOrderItemPrice.text = "51$"
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }
}
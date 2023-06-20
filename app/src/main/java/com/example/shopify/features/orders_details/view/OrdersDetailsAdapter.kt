package com.example.shopify.features.orders_details.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.databinding.OrderDetailsCardBinding

class OrdersDetailsAdapter (private val context: Context,private val images: List<String>) : RecyclerView.Adapter<OrdersDetailsAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: OrderDetailsCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = OrderDetailsCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(images[position]).into(holder.binding.productDetailsImage)
    }
}
package com.example.shopify.features.orders_details.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.databinding.OrderDetailsCardBinding
import com.example.shopify.features.orders.model.model_response.LineItem

class OrdersDetailsAdapter(private val items: List<LineItem>) :
    RecyclerView.Adapter<OrdersDetailsAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: OrderDetailsCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = OrderDetailsCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.productTitleTV.text = items[position].name?.split("|")?.last()?.trim()
        holder.binding.productColorTV.text = items[position].variant_title?.split("/")?.get(1)?.trim() ?: ""
        holder.binding.productSizeTV.text = items[position].variant_title?.split("/")?.get(0)?.trim() ?: ""
        holder.binding.productUnitsTV.text = items[position].quantity.toString()
        holder.binding.productTotalPriceTV.text = items[position].price
    }
}
package com.example.shopify.features.orders.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.databinding.OrderCardBinding
import com.example.shopify.features.orders.model.model_response.LineItem
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrdersAdapter(
    private val orders: List<OrderResponseData>,
    private val orderInterface : IOrderDetails

) : RecyclerView.Adapter<OrdersAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: OrderCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = OrderCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.orderNumberTV.text = orders[position].order_number.toString()
        holder.binding.orderIdTV.text = orders[position].id.toString()
        holder.binding.totalPriceTV.text = orders[position].total_price
        holder.binding.quantityTV.text = getTotalQuantity(orders[position].line_items)
        holder.binding.dateTV.text = formatDate(orders[position].created_at)
        holder.binding.detailsBtn.setOnClickListener {
            orderInterface.navigateToOrderDetails(orders[position])
        }
    }

    private fun getTotalQuantity (items : List<LineItem>) : String {
        var totalQuantity =0
        for (lineItem in items) {
            totalQuantity += lineItem.quantity
        }
        return totalQuantity.toString()
    }

    private fun formatDate(unFormattedDate : String) : String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        val dateTime = dateFormat.parse(unFormattedDate)
        val cal = Calendar.getInstance()
        cal.time = dateTime
        val date = cal.get(Calendar.DATE)
        val month = cal.get(Calendar.MONTH) + 1
        val year = cal.get(Calendar.YEAR)
        return String.format("%04d-%02d-%02d", year, month, date)
    }
}
package com.example.shopify.features.orders_details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.util.Constants
import com.example.shopify.databinding.FragmentOrdersDetailsBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.orders.model.model_response.LineItem
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrdersDetailsFragment : Fragment() {


    private lateinit var binding: FragmentOrdersDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: OrdersDetailsFragmentArgs =
            OrdersDetailsFragmentArgs.fromBundle(requireArguments())
        initOrdersUI(args.selectedOrder)
        val list = mutableListOf<LineItem>()
        list.addAll(args.selectedOrder.line_items ?: listOf())
        list.removeAt(0)
        //binding.bindingOrderDetailsAdapter = OrdersDetailsAdapter(args.selectedOrder.line_items ?: listOf())
        binding.bindingOrderDetailsAdapter = OrdersDetailsAdapter(list)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)
        (activity as MainActivity).binding.toolbar.findViewById<SearchView>(R.id.searchView).visibility = View.GONE
        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initOrdersUI(order: OrderResponseData) {
        val address = order.billingAddress
        binding.orderIdTV.text = order.id.toString()
        binding.itemCountTV.text = getTotalQuantity(order.line_items ?: listOf())
        binding.deliveryDetailsTV.text = Constants.DELIVERY_CHARGE_USD.toString()
        binding.orderTotalPriceTV.text = order.total_price
        binding.orderNumberTV.text = order.order_number.toString()
        binding.dateTV.text = formatDate(order.created_at ?: "")
        binding.addressTV.text = getString(
            R.string.formatted_address,
            address?.address1,
            address?.city,
            address?.country,
            address?.phone
        )


        val t = (order.total_price?.toDouble()?.plus(Constants.DELIVERY_CHARGE_USD) ?: 0.0) + Constants.EXTRA_CHARGES_IN_USD
        //println(t)

        binding.extraChargeTV.text = when (order.shipping_lines?.get(0)?.title) {
            Constants.SHIPPING_LINE_EXTRA_CHARGES -> Constants.EXTRA_CHARGES_IN_USD.toString()
            Constants.SHIPPING_LINES_C0D -> "_"
            else -> "0"
        }

        when {
            order.discount_applications.isNullOrEmpty() -> {
                binding.discountTV.text = "_"
            }

            order.discount_applications[0].value_type == "fixed_amount" -> {
                binding.discountTV.text =
                    "${order.discount_applications[0].value} ${order.currency},${order.discount_applications[0].title}"
            }

            else -> {
                binding.discountTV.text = getString(
                    R.string.formatted_discount_percentage,
                    order.discount_applications[0].value,
                    order.discount_applications[0].title
                )
            }
        }

    }

    private fun getTotalQuantity(items: List<LineItem>): String {
        var totalQuantity = -1
        for (lineItem in items) {
            totalQuantity += lineItem.quantity ?: 0
        }
        return totalQuantity.toString()
    }

    private fun formatDate(unFormattedDate: String): String {
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
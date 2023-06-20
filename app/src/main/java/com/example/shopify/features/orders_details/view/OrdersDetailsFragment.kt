package com.example.shopify.features.orders_details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shopify.R
import com.example.shopify.databinding.FragmentOrdersDetailsBinding
import com.example.shopify.features.orders.model.model_response.LineItem
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import com.example.shopify.features.orders_details.network.OrderDetailsClient
import com.example.shopify.features.orders_details.repository.OrdersDetailsRepository
import com.example.shopify.features.orders_details.viewmodel.OrdersDetailsViewModel
import com.example.shopify.features.orders_details.viewmodel.OrdersDetailsViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrdersDetailsFragment : Fragment() {

    private val ordersDetailsViewModel by lazy {
        val ordersDetailsViewModelFactory =
            OrdersDetailsViewModelFactory(
                OrdersDetailsRepository.getInstance(OrderDetailsClient.getInstance())
            )
        ViewModelProvider(
            this,
            ordersDetailsViewModelFactory
        ).get(OrdersDetailsViewModel::class.java)
    }


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
        lifecycleScope.launch {
            val images = ordersDetailsViewModel.getProductsImages(args.selectedOrder.line_items)
            println(images[0])
            binding.bindingOrderDetailsAdapter = OrdersDetailsAdapter(requireContext(), images)
        }

    }


    private fun initOrdersUI(order: OrderResponseData) {
        val address = order.customer?.default_address
        binding.orderIdTV.text = order.id.toString()
        binding.itemCountTV.text = getTotalQuantity(order.line_items)
        binding.orderTotalPriceTV.text = order.total_price
        binding.orderNumberTV.text = order.order_number.toString()
        binding.dateTV.text = formatDate(order.created_at)
        binding.addressTV.text = getString(
            R.string.formatted_address,
            address?.address1,
            address?.province,
            address?.city,
            address?.country
        )

        if (order.discount_codes.isNullOrEmpty()) {
            binding.discountTV.text = "_"
        } else if (order.discount_applications[0].value_type == "fixed_amount") {
            binding.discountTV.text =
                "${order.discount_applications[0].value} ${order.currency},${order.discount_applications[0].title}"
        } else {
            binding.discountTV.text = getString(
                R.string.formatted_discount_percentage,
                order.discount_applications[0].value,
                order.discount_applications[0].title
            )
        }
    }

    private fun getTotalQuantity(items: List<LineItem>): String {
        var totalQuantity = 0
        for (lineItem in items) {
            totalQuantity += lineItem.quantity
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
package com.example.shopify.features.orders.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.core.util.ApiState
import com.example.shopify.databinding.FragmentOrdersBinding
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import com.example.shopify.features.orders.network.OrderClient
import com.example.shopify.features.orders.repository.OrdersRepository
import com.example.shopify.features.orders.viewmodel.OrdersViewModel
import com.example.shopify.features.orders.viewmodel.OrdersViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OrdersFragment : Fragment(), IOrderDetails {

    private val ordersViewModel by lazy {
        val ordersViewModelFactory = OrdersViewModelFactory(
            OrdersRepository.getInstance(OrderClient.getInstance())
        )
        ViewModelProvider(this, ordersViewModelFactory).get(OrdersViewModel::class.java)
    }

    private lateinit var binding: FragmentOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        ordersViewModel.getFlowOrders(CustomerId(7063229432127))

        lifecycleScope.launch {
            ordersViewModel.orders.collectLatest { state ->
                when (state) {
                    is ApiState.Success<*> -> {
                        initOrders(state.myData as List<OrderResponseData>)
                    }

                    is ApiState.Failure -> {
                        showError()
                    }

                    else -> {
                        loadingUI()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun initOrders(orders: List<OrderResponseData>) {
        binding.ordersProgress.visibility = View.GONE
        if (orders.isEmpty()) {
            binding.noOrdersTV.text = getString(R.string.noOrders)
            binding.noOrdersTV.visibility = View.VISIBLE

        } else {
            binding.bindingAdapter = OrdersAdapter(orders, this)
            binding.noOrdersTV.visibility = View.GONE
            binding.ordersRec.visibility = View.VISIBLE
        }
    }

    private fun loadingUI() {
        binding.ordersProgress.visibility = View.VISIBLE
        binding.ordersRec.visibility = View.GONE
        binding.noOrdersTV.visibility = View.GONE
    }

    private fun showError() {
        binding.ordersProgress.visibility = View.GONE
        binding.ordersRec.visibility = View.GONE
        binding.noOrdersTV.text = getString(R.string.networkError)
        binding.noOrdersTV.visibility = View.VISIBLE
    }

    override fun navigateToOrderDetails(order: OrderResponseData) {
        findNavController().navigate(
            OrdersFragmentDirections.actionOrdersFragmentToOrdersDetailsFragment(
                order
            )
        )
    }

}
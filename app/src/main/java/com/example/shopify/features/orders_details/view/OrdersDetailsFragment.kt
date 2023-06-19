package com.example.shopify.features.orders_details.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopify.R
import com.example.shopify.features.orders_details.viewmodel.OrdersDetailsViewModel

class OrdersDetailsFragment : Fragment() {

    private lateinit var viewModel: OrdersDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrdersDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.example.shopify.features.shoppingcart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.features.MainActivity
import com.example.shopify.features.shoppingcart.model.Order
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class ShoppingCartFragment : Fragment() {

    private lateinit var rvOrderItems: RecyclerView
    private lateinit var adapter: OrderItemsAdapter
    private lateinit var promocodesMenu: MaterialAutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        promocodesMenu = view.findViewById(R.id.actv_promocoes)
        rvOrderItems = view.findViewById(R.id.rv_shopping_cart_orders)
        val orders = listOf(
            Order("Pullover","Black","L","51"),
            Order("T-Shirt","Gray","L","30"),
            Order("Pullover","Black","M","43"),
            Order("Pullover","Black","L","51"),
            Order("T-Shirt","Gray","L","30"),
            Order("Pullover","Black","M","43")
        )
        promocodesMenu.setSimpleItems(arrayOf("Item1","Item2","Item3","Item4","Item5"))
        adapter = OrderItemsAdapter(orders,requireContext())
        rvOrderItems.layoutManager = LinearLayoutManager(requireContext())
        rvOrderItems.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.navView.visibility = View.VISIBLE
    }
}
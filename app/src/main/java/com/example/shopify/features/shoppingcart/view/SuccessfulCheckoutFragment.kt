package com.example.shopify.features.shoppingcart.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SuccessfulCheckoutFragment : Fragment() {

    private lateinit var btnContinueShopping: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_successful_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnContinueShopping = view.findViewById(R.id.btn_continue_shopping)
        btnContinueShopping.setOnClickListener {
            findNavController().navigate(
                SuccessfulCheckoutFragmentDirections.actionSuccessfulCheckoutFragmentToNavigationHome()
            )
        }
    }
}
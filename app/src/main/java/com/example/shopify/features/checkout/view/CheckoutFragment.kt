package com.example.shopify.features.checkout.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.shopify.R
import com.example.shopify.databinding.FragmentCheckoutBinding
import com.google.android.material.card.MaterialCardView

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUiListeners()

    }

    private fun setUiListeners() {
        binding.tfStreetName.doOnTextChanged { _, _, _, _ ->
            if (binding.tfStreetName.text?.isEmpty() == true) {
                binding.tfStreetName.error = "Required"
            }
        }

        binding.tfCity.doOnTextChanged { _, _, _, _ ->
            if (binding.tfCity.text?.isEmpty() == true) {
                binding.tfCity.error = "Required"
            }
        }

        binding.tfCountry.doOnTextChanged { _, _, _, _ ->
            if (binding.tfCountry.text?.isEmpty() == true) {
                binding.tfCountry.error = "Required"
            }
        }

        binding.cvPayByCredit.setOnClickListener {

            binding.cvPayByCash.strokeWidth = 0

            (it as MaterialCardView).strokeWidth = 2
            (it as MaterialCardView).strokeColor = resources.getColor(R.color.primaryRed)
        }

        binding.cvPayByCash.setOnClickListener {

            binding.cvPayByCredit.strokeWidth = 0

            (it as MaterialCardView).strokeWidth = 2
            (it as MaterialCardView).strokeColor = resources.getColor(R.color.primaryRed)
        }

        binding.btnSubmitOrder.setOnClickListener {
            if(areTextFieldsFilled()){
                // TODO place an order and navigate somewhere else
            }
        }
    }

    private fun areTextFieldsFilled() : Boolean{
        var canSave = true
        if(binding.tfStreetName.text?.isEmpty() == true){
            canSave = false
        }
        if(binding.tfCity.text?.isEmpty() == true){
            canSave = false
        }
        if(binding.tfCountry.text?.isEmpty() == true){
            canSave = false
        }
        return canSave
    }
}
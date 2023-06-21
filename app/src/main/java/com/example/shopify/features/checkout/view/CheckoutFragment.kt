package com.example.shopify.features.checkout.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.shopify.R
import com.example.shopify.core.common.data.model.PreplacedOrder
import com.example.shopify.core.util.Constants
import com.example.shopify.databinding.FragmentCheckoutBinding
import com.google.android.material.card.MaterialCardView

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
   // private val args = CheckoutFragmentArgs.fromBundle(requireArguments())
    private var _extraChargesInUsd = 0.0
    private var initialOrdersPrice = 0.0
    private var promocode = 0.0
    private var subtotal = 0.0
    private var total = 0.0

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
        val args = CheckoutFragmentArgs.fromBundle(requireArguments())
        binding.tvCheckoutTotalValue.text = getCheckoutTotal(args.preplacedOrder).toString()
        setOrderValues()

        Log.i("Exception", "${args.preplacedOrder}\n${args.promocode}")

    }

    private fun getCheckoutTotal(preplacedOrder: Array<PreplacedOrder>?) : Double{

        for(order in preplacedOrder!!){
            initialOrdersPrice += order.price?.toDouble() ?: 0.0
        }

        //promocode = args.promocode?.value?.times(initialOrdersPrice) ?: 0.0
        subtotal = initialOrdersPrice - promocode
        total = subtotal + _extraChargesInUsd + Constants.DELIVERY_CHARGE_USD

        return total
    }

    private fun setOrderValues(){
        binding.tvOrderValue.text = initialOrdersPrice.toString()
        binding.tvCheckoutPromocodeValue.text = promocode.toString()
        binding.tvCheckoutSubtotalValue.text = subtotal.toString()
        binding.tvCheckoutDeliveryValue.text = Constants.DELIVERY_CHARGE_USD.toString()
        binding.tvCheckoutTotalValue.text = total.toString()
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

            _extraChargesInUsd = 15.0
            binding.tvCheckoutExtraChargersValue.text = _extraChargesInUsd.toString()
            binding.tvCheckoutTotalValue.text =
                binding.tvCheckoutTotalValue.text.toString().toDouble().plus(_extraChargesInUsd).toString()
        }

        binding.cvPayByCash.setOnClickListener {

            binding.cvPayByCredit.strokeWidth = 0

            (it as MaterialCardView).strokeWidth = 2
            (it as MaterialCardView).strokeColor = resources.getColor(R.color.primaryRed)
            binding.tvCheckoutExtraChargersValue.text = "0"
            binding.tvCheckoutTotalValue.text =
                binding.tvCheckoutTotalValue.text.toString().toDouble().minus(_extraChargesInUsd).toString()

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
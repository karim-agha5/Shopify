package com.example.shopify.features.checkout.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopify.BuildConfig
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CheckoutBillingAddress
import com.example.shopify.core.common.data.model.CheckoutCustomer
import com.example.shopify.core.common.data.model.CheckoutDiscountCode
import com.example.shopify.core.common.data.model.CheckoutLineItem
import com.example.shopify.core.common.data.model.CheckoutOrder
import com.example.shopify.core.common.data.model.CheckoutShippingLines
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.data.model.Discount
import com.example.shopify.core.common.data.model.PreplacedOrder
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.remote.DraftOrderRemoteSourceImpl
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.common.mappers.LineItemsMapper
import com.example.shopify.core.util.ApiState2
import com.example.shopify.core.util.Constants
import com.example.shopify.databinding.FragmentCheckoutBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.checkout.data.CheckoutRepositoryImpl
import com.example.shopify.features.checkout.data.remote.CheckoutRemoteService
import com.example.shopify.features.checkout.data.remote.CheckoutRemoteSourceImpl
import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.paymentgateway.stripe.remote.StripeRemoteSourceImpl
import com.example.shopify.features.checkout.paymentgateway.stripe.service.StripeRetrofitHelper
import com.example.shopify.features.checkout.paymentgateway.stripe.service.StripeService
import com.example.shopify.features.checkout.viewmodel.CheckoutViewModel
import com.example.shopify.features.checkout.viewmodel.CheckoutViewModelFactory
import com.example.shopify.features.shoppingcart.domain.DraftOrder
import com.example.shopify.features.shoppingcart.view.OrderItemsAdapter
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var body: CheckoutOrderRequest
    private val checkoutViewModel by lazy {
        val service = RetrofitHelper.getInstance().create(CheckoutRemoteService::class.java)
        val remoteSource = CheckoutRemoteSourceImpl(service)
        val repo = CheckoutRepositoryImpl(remoteSource)
        val factory = CheckoutViewModelFactory(repo)
        ViewModelProvider(this, factory).get(CheckoutViewModel::class.java)
    }
    private val stripeRemoteSource by lazy{
        StripeRemoteSourceImpl(StripeRetrofitHelper.getInstance().create(StripeService::class.java))
    }
    private lateinit var paymentSheet: PaymentSheet
    private var stripeCustomerId: String? = null
    private var stripeEphemeralKey: String? = null
    private var stripeClientSecret: String? = null
    private var isDeliveryMethodChosen = false
    private var _customer: CustomerResponseInfo? = null
    private var _preplacedOrders: Array<PreplacedOrder>? = null
    private var _promocode: Discount? = null
    private var _shippingLineType = Constants.SHIPPING_LINES_C0D
    private var areExtraChargesAdded = false
    private var initialOrdersPrice = 0.0
    private var promocode = 0.0
    private var subtotal = 0.0
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStripeSuccessfulPaymentConfirmation()
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
        val customer = (activity as MainActivity).customerInfo
        val args = CheckoutFragmentArgs.fromBundle(requireArguments())
        _customer = customer
        _preplacedOrders = args.preplacedOrder
        _promocode = args.promocode
        binding.tvCheckoutTotalValue.text = getCheckoutTotal(args.preplacedOrder).toString()
        setOrderValues()

        lifecycleScope.launch {
            checkoutViewModel.orderCheckoutState.collect {
                when (it) {
                    is ApiState2.Loading -> {/*Do Nothing*/
                    }

                    is ApiState2.Success -> {
                        displaySuccessDialog()
                        clearShoppingCart()
                    }

                    is ApiState2.Failure -> {
                        Log.i(
                            "Exception",
                            "Can't place the order inside ${this@CheckoutFragment::class.java.simpleName}\n" +
                                    "exception message -> ${it.exception.message}"
                        )
                    }
                }
            }
        }

        lifecycleScope.launch {
            loadUserSettingsFromDataStore()
        }
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
    private fun getCheckoutTotal(preplacedOrder: Array<PreplacedOrder>?): Double {

        // TODO remove later when you adjust navigation
        initialOrdersPrice = 0.0
        for (order in preplacedOrder!!) {
            initialOrdersPrice +=
                (order.price?.toDouble() ?: 0.0).times(order.quantity ?: 0)
        }

        subtotal = initialOrdersPrice - promocode
        total = subtotal + Constants.DELIVERY_CHARGE_USD

        return total
    }

    private fun setOrderValues() {
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

        binding.tfPhone.doOnTextChanged { _, _, _, _ ->
            if (binding.tfPhone.text?.isEmpty() == true) {
                binding.tfPhone.error = "Required"
            }
        }

        binding.cvPayByCredit.setOnClickListener {

            binding.cvPayByCash.strokeWidth = 0

            (it as MaterialCardView).strokeWidth = 2
            it.strokeColor = ContextCompat.getColor(requireContext(),R.color.primaryRed)

            if(!areExtraChargesAdded){
                binding.tvCheckoutExtraChargersValue.text = Constants.EXTRA_CHARGES_IN_USD.toString()
                total = binding.tvCheckoutTotalValue.text.toString().toDouble().plus(Constants.EXTRA_CHARGES_IN_USD)
                binding.tvCheckoutTotalValue.text = total.toString()
                _shippingLineType = Constants.SHIPPING_LINE_EXTRA_CHARGES
            }
            areExtraChargesAdded = true
            isDeliveryMethodChosen = true
        }

        binding.cvPayByCash.setOnClickListener {

            binding.cvPayByCredit.strokeWidth = 0

            (it as MaterialCardView).strokeWidth = 2
            it.strokeColor = ContextCompat.getColor(requireContext(),R.color.primaryRed)
            binding.tvCheckoutExtraChargersValue.text = "0"
            if(areExtraChargesAdded){
                binding.tvCheckoutTotalValue.text =
                    binding.tvCheckoutTotalValue.text.toString().toDouble().minus(Constants.EXTRA_CHARGES_IN_USD)
                        .toString()
                _shippingLineType = Constants.SHIPPING_LINES_C0D
            }
            areExtraChargesAdded = false
            isDeliveryMethodChosen = true
        }

        binding.btnSubmitOrder.setOnClickListener {
            if (areTextFieldsFilled()) {
                // TODO place an order and navigate somewhere else
                if(isDeliveryMethodChosen){
                    // If credit card method is chosen, launch stripe payment gateway method
                    if(areExtraChargesAdded){
                        startStripePaymentSheet()
                    }
                    else{
                        setCheckoutOrderBody()
                        checkoutViewModel.createOrder(body)
                    }

                }
                else{
                    Toast.makeText(requireContext(), R.string.delivery_method_not_chosen_message, Toast.LENGTH_SHORT).show()
                }
            } else {

                if (binding.tfStreetName.text?.isEmpty() == true) {
                    binding.tfStreetName.error = "Required"
                }

                if (binding.tfCity.text?.isEmpty() == true) {
                    binding.tfCity.error = "Required"
                }

                if (binding.tfCountry.text?.isEmpty() == true) {
                    binding.tfCountry.error = "Required"
                }

                if (binding.tfPhone.text?.isEmpty() == true) {
                    binding.tfPhone.error = "Required"
                }

                binding.svCheckout.post { binding.svCheckout.scrollTo(0,0) }

            }
        }
    }

    private fun areTextFieldsFilled(): Boolean {
        var canCheckout = true
        if (binding.tfStreetName.text?.isEmpty() == true) {
            canCheckout = false
        }
        if (binding.tfCity.text?.isEmpty() == true) {
            canCheckout = false
        }
        if (binding.tfCountry.text?.isEmpty() == true) {
            canCheckout = false
        }
        if (binding.tfPhone.text?.isEmpty() == true) {
            canCheckout = false
        }
        return canCheckout
    }

    private suspend fun loadUserSettingsFromDataStore(){
        val userSettingsDataStore = (activity as MainActivity).userSettingsDataStore
        binding.tfBuildingNumber.setText(userSettingsDataStore.readUserBuildingNumber())
        binding.tfStreetName.setText(userSettingsDataStore.readUserStreetName())
        binding.tfCity.setText(userSettingsDataStore.readUserCity())
        binding.tfCountry.setText(userSettingsDataStore.readUserCountry())
        binding.tfPhone.setText(userSettingsDataStore.readUserPhoneNumber())
        Log.i("Exception", "Inside Checkout Fragment:\n " +
                "${userSettingsDataStore.readUserBuildingNumber()} \n" +
                "${userSettingsDataStore.readUserStreetName()} \n" +
                "${userSettingsDataStore.readUserCity()} \n" +
                "${userSettingsDataStore.readUserCountry()} \n" +
                "${userSettingsDataStore.readUserPhoneNumber()} \n"
        )
    }

    private fun setCheckoutOrderBody() {
        val lineItems = mutableListOf<CheckoutLineItem>()
        if (_preplacedOrders != null) {
            for (order in _preplacedOrders!!) {
                lineItems.add(
                    CheckoutLineItem(
                        order.id,
                        order.variantId,
                        order.productId,
                        order.title,
                        order.variantTitle,
                        order.quantity,
                        order.name,
                        order.price,
                        "EGP"
                    )
                )
            }
        }
        val checkoutCustomer = CheckoutCustomer(_customer?.id ?: 0, _customer?.email ?: "")
            val discountCode =
                CheckoutDiscountCode(_promocode?.value?.toString(), _promocode?.description, _promocode?.valueType)

        // TODO change firstname or lastname in the future if needed
        val billingAddress = CheckoutBillingAddress(
            "test",
            "test",
            "${binding.tfBuildingNumber.text.toString()} ${binding.tfStreetName.text.toString()}",
            binding.tfPhone.text.toString(),
            binding.tfCity.text.toString(),
            binding.tfCountry.text.toString()
        )
        val shippingLine: CheckoutShippingLines
        if(_shippingLineType == Constants.SHIPPING_LINES_C0D){
            shippingLine =
                CheckoutShippingLines(_shippingLineType,
                    Constants.DELIVERY_CHARGE_USD.toDouble(),
                    "standard")
        }

        else{
            shippingLine =
                CheckoutShippingLines(
                    _shippingLineType,
                    Constants.DELIVERY_CHARGE_USD.toDouble() + Constants.EXTRA_CHARGES_IN_USD,
                    "standard")
        }

        val checkoutOrder = CheckoutOrder(
            lineItems,
            checkoutCustomer,
            "EGP",
            billingAddress,
            listOf(discountCode),
            listOf(shippingLine)
        )
        Log.i("Exception", "type = ${discountCode.type}\n" +
                "amount = ${discountCode.amount}\n" +
                "code = ${discountCode.code}")
        Log.i("Exception", "$checkoutOrder")
        body = CheckoutOrderRequest(checkoutOrder)
    }
    private fun displaySuccessDialog(){
        val dialog = MaterialAlertDialogBuilder(requireContext(),R.layout.successful_checkout_dialog)
            .setView(layoutInflater.inflate(R.layout.successful_checkout_dialog,null))
            .setCancelable(false)
            .show()

        val btnContinue: Button? = dialog.findViewById(R.id.btn_dialog_continue)
        btnContinue?.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(
                CheckoutFragmentDirections.actionCheckoutFragmentToSuccessfulCheckoutFragment()
            )
        }

        Handler()
            .postDelayed({
                val tvMessage: TextView? =
                    dialog.findViewById(R.id.tv_successful_checkout_dialog_message)
                tvMessage?.text = resources.getString(R.string.successful_checkout_dialog_message)
                btnContinue?.visibility = View.VISIBLE
            },3000)

    }

    private fun startStripePaymentSheet(){
        this@CheckoutFragment.lifecycleScope.launch(Dispatchers.IO) {
            //stripeCustomerId = stripeRemoteSource.createStripeCustomer()?.id
            stripeCustomerId = "cus_O8AQGygYXQNaRA"
            Log.i("Exception", "stripeCustomerId = $stripeCustomerId")
            stripeEphemeralKey = stripeRemoteSource.getEphemeralKey(stripeCustomerId ?: "")?.id
            Log.i("Exception", "ephemeral key = $stripeEphemeralKey")
            stripeClientSecret = stripeRemoteSource.submitPaymentIntent(
                stripeCustomerId ?: "",
                total.times(100).toLong(),
                "usd"
            )?.clientSecret
            Log.i("Exception", "client secret = $stripeClientSecret")

            val customerConfiguration = PaymentSheet.CustomerConfiguration(
                stripeCustomerId ?: "",stripeEphemeralKey ?: ""
            )
            val billingDetails = PaymentSheet.BillingDetails(
                PaymentSheet.Address(country = "EG")
            )
            val paymentSheetConfiguration =
                PaymentSheet.Configuration(
                    merchantDisplayName = "E-Shoppers",
                    customer = customerConfiguration,
                    defaultBillingDetails = billingDetails
                )

            paymentSheet.presentWithPaymentIntent(
                stripeClientSecret ?: "", paymentSheetConfiguration
            )

        }
    }
    private fun onStripeSuccessfulPaymentConfirmation(){
        PaymentConfiguration.init(requireContext(), BuildConfig.STRIPE_PUBLISHABLE_KEY)
        paymentSheet = PaymentSheet(this){
            if(it is PaymentSheetResult.Completed){
                setCheckoutOrderBody()
                checkoutViewModel.createOrder(body)
            }
        }
    }
    private fun clearShoppingCart(){
       val remoteSource = DraftOrderRemoteSourceImpl(RetrofitHelper.getInstance())
       val draftOrderRepository = DraftOrderRepositoryImpl(remoteSource)
        val draftOrder = DraftOrder(draftOrderRepository)
        lifecycleScope.launch(Dispatchers.IO){
            val response = draftOrderRepository.getShoppingCart(_customer?.cartId?.toString() ?: "")
            response
                .catch {
                    Log.i("Exception", "Inside ${this@CheckoutFragment::class.java.simpleName} -> " +
                            "${it.message}")
                }
                .collect{
                    val draft = it.draftOrder
                    draftOrder.clearShoppingCart(_customer)
                }
        }
    }
}
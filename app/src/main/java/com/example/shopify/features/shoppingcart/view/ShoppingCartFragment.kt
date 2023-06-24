package com.example.shopify.features.shoppingcart.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.data.model.Discount
import com.example.shopify.core.common.data.model.PreplacedOrder
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.remote.DraftOrderRemoteSourceImpl
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.util.ApiState2
import com.example.shopify.databinding.FragmentShoppingCartBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.shoppingcart.domain.DraftOrder
import com.example.shopify.features.shoppingcart.view.interfaces.CartOrderItemHandler
import com.example.shopify.features.shoppingcart.view.interfaces.TotalAmountHandler
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartListItemsViewModel
import com.example.shopify.features.shoppingcart.viewmodel.factory.ShoppingCartListItemsViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "Exception"
class ShoppingCartFragment : Fragment(), CartOrderItemHandler, TotalAmountHandler {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var adapter: OrderItemsAdapter
    private var customer: CustomerResponseInfo? = null
    private var auth = FirebaseAuth.getInstance()
    private val orders = mutableListOf<ModifyDraftOrderResponseLineItem>()
    private val draftOrder by lazy {
        val remoteSource = DraftOrderRemoteSourceImpl(RetrofitHelper.getInstance())
        val repo = DraftOrderRepositoryImpl(remoteSource)
        DraftOrder(repo)
    }
    private val shoppingCartListItemsViewModel by lazy {
        val remoteSource = DraftOrderRemoteSourceImpl(RetrofitHelper.getInstance())
        val repo = DraftOrderRepositoryImpl(remoteSource)
        val factory = ShoppingCartListItemsViewModelFactory(repo,draftOrder)
        ViewModelProvider(this, factory).get(ShoppingCartListItemsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_shopping_cart,container,false)
        binding.cartFragment = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAuthenticationButtonListeners()
        val user = auth.currentUser

        // If the user isn't logged in
        if(user == null){
            displayLoggedOutLayout()
        }
        
        // If the user is logged in
        else{
            customer = (activity as MainActivity).customerInfo
            (binding.actvPromocoes as MaterialAutoCompleteTextView)
                .setSimpleItems(arrayOf("Item1","Item2","Item3","Item4","Item5"))
            adapter = OrderItemsAdapter(mutableListOf(),this,this,requireContext())
            binding.adapter = adapter

            shoppingCartListItemsViewModel.getShoppingCart(customer?.cartId?.toString() ?: "")
            lifecycleScope.launch {
                shoppingCartListItemsViewModel.listItemsStateFlow.collectLatest{
                    when(it){
                        is ApiState2.Loading -> {/*Do Nothing*/ }
                        is ApiState2.Success -> {
                            /*
                            * Hide the loading progress indicator when the response is ready,
                            * save the order items in the remote shopping cart in a local in-memory list,
                            * set the initial total amount value using the orders' prices,
                            * and display the list in the orders recyclerview
                            **/
                            binding.shoppingCartLayoutProgressBar.visibility = View.GONE
                            if((it.data?.lineItems?.size ?: 0) <= 1){
                                displayEmptyShoppingCartLayout()
                            }
                            else {
                                displayLoggedInLayout()
                            }
                                orders.clear()
                                orders.addAll(it.data?.lineItems ?: mutableListOf())
                                adapter.submitList(orders)
                                adapter.notifyDataSetChanged()
                                binding.indeterminateCircularProgressIndicator.visibility = View.GONE
                                setInitialTotalAmountValue()

                        }
                        is ApiState2.Failure -> {
                            binding.indeterminateCircularProgressIndicator.visibility = View.GONE
                            binding.shoppingCartLayoutProgressBar.visibility = View.GONE
                            showShoppingCartErrorDialog()
                        }
                    }
                }
            }



            binding.btnCheckout.setOnClickListener{
                val discount = Discount(
                    "test",
                    "test",
                    "percentage",
                    50,
                    "test"
                )
                findNavController().navigate(
                   ShoppingCartFragmentDirections.actionShoppingCartFragmentToCheckoutFragment2(
                       getPreplacedOrdersArray(),discount
                   )
                    )

            }


        }






    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.navView.visibility = View.VISIBLE
//        (activity as MainActivity).binding.toolbar.navigationIcon = null
        (activity as MainActivity).binding.toolbar.visibility = View.GONE
    }

    private fun setAuthenticationButtonListeners(){
        binding.btnShoppingCartLogin.setOnClickListener {
            findNavController().navigate(
                ShoppingCartFragmentDirections.actionShoppingCartFragmentToLoginFragment()
            )
        }
        binding.btnShoppingCartSignup.setOnClickListener {
            findNavController().navigate(
                ShoppingCartFragmentDirections.actionShoppingCartFragmentToRegistrationFragment()
            )
        }
    }

    private fun setInitialTotalAmountValue(){
        var total = 0.0
        for(order in orders){
            total += (order.price?.toDouble() ?: 0.0).times(order.requestedQuantity ?: 0)

        }
        binding.tvTotalAmountValue.text = "$total"
    }

    private fun getPreplacedOrdersArray() : Array<PreplacedOrder>{
        val preplacedOrdersArray = Array<PreplacedOrder>(orders.size){
             PreplacedOrder(
                orders[it].id,
                orders[it].variantId,
                orders[it].productId,
                orders[it].title,
                orders[it].variantTitle,
                orders[it].requestedQuantity,
                orders[it].name,
                orders[it].price
            )
        }
        return preplacedOrdersArray
    }

    override fun removeOrder(order: ModifyDraftOrderResponseLineItem) {
        /*orders.remove(order)
        modifyRemoteShoppingCart(null,-1)*/
        shoppingCartListItemsViewModel.removeOrder(customer,order)
    }

    /*
    * Increments the actual quantity of an order and modifies the remote draft order
    * */
    override fun incrementOrder(order: ModifyDraftOrderResponseLineItem,position: Int) {
        /*val incrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.plus(1))
        modifyRemoteShoppingCart(incrementedOrder,position)*/
        binding.indeterminateCircularProgressIndicator.visibility = View.VISIBLE
        adapter = OrderItemsAdapter(mutableListOf(),this,this,requireContext())
        binding.adapter = adapter
        shoppingCartListItemsViewModel.incrementOrder(customer,order,position)
    }

    override fun decrementOrder(order: ModifyDraftOrderResponseLineItem,position: Int) {

       /* val decrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.minus(1))
        modifyRemoteShoppingCart(decrementedOrder,position)*/
        binding.indeterminateCircularProgressIndicator.visibility = View.VISIBLE
        adapter = OrderItemsAdapter(mutableListOf(),this,this,requireContext())
        binding.adapter = adapter
        shoppingCartListItemsViewModel.decrementOrder(customer,order,position)
    }

  /*  private fun modifyRemoteShoppingCart(order: ModifyDraftOrderResponseLineItem?,position: Int){
        *//*
       * A work around to update each item's quantity to avoid multiple clicks on the same button.
       * Multiple clicks lead to items duplication in the recyclerview.
       * Adding an circular loading progress to each recyclerview item lead to unexpected behaviors.
       * *//*
        binding.indeterminateCircularProgressIndicator.visibility = View.VISIBLE
        adapter = OrderItemsAdapter(mutableListOf(),this,this,requireContext())
        binding.adapter = adapter

        val tempList = orders.toMutableList()
        if(order != null) {
            tempList[position] = order
        }
        val requestLineItemsList = LineItemsMapper.fromResponseToRequestLineItems(tempList)
        val requestDraftOrder = ModifyDraftOrderRequestDraftOrder(
            null,
            "test@hotmail.com",
            true,
            requestLineItemsList,
            null
        )
        val body = ModifyDraftOrderRequestBody(requestDraftOrder)
        shoppingCartListItemsViewModel.modifyShoppingCart(customer?.cartId?.toString() ?: "",body)
    }*/

    override fun adjustPrice(price: Double?) {
        binding
            .tvTotalAmountValue
            .text = "${binding.tvTotalAmountValue.text.toString().toDouble() + (price ?: 0.0)}"
    }
    
    private fun showShoppingCartErrorDialog(){
        MaterialAlertDialogBuilder(requireContext(),R.style.MyDialogTheme)
            .setTitle("Error")
            .setMessage("Unable to retrieve the shopping cart.")
            .setNeutralButton("OK"){ dialog,_->
                dialog.dismiss()
            }
            .show()

    }
    
    fun navigateToLogin(view: View){
//        findNavController().navigate(ShoppingCartFragmentDirections.action_shoppingCartFragment_to_loginFragment())
    }
    
    fun navigateToRegister(view: View){
//        findNavController().navigate(ShoppingCartFragmentDirections.action_shoppingCartFragment_to_registrationFragment())
    }
    
    private fun displayEmptyShoppingCartLayout(){
        binding.loggedInLayout.visibility = View.GONE
        binding.emptyCartAnimation.visibility = View.VISIBLE
    }

    private fun displayLoggedOutLayout(){
        binding.loggedOutLayout.visibility = View.VISIBLE
        binding.loggedInLayout.visibility = View.GONE
        binding.shoppingCartLayoutProgressBar.visibility = View.GONE
    }

    private fun displayLoggedInLayout(){
        binding.loggedInLayout.visibility = View.VISIBLE
        binding.loggedOutLayout.visibility = View.GONE
        binding.shoppingCartLayoutProgressBar.visibility = View.GONE
    }
}
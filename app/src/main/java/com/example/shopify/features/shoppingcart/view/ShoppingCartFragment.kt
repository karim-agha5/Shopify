package com.example.shopify.features.shoppingcart.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomerFirebase
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.getVariantOptions
import com.example.shopify.databinding.FragmentShoppingCartBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.core.common.features.draftorder.data.remote.ShoppingCartRemoteSourceImpl
import com.example.shopify.core.common.features.draftorder.data.ShoppingCartRepositoryImpl
import com.example.shopify.core.common.features.draftorder.model.Order
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestLineItem
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.common.mappers.LineItemsMapper
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartListItemsViewModel
import com.example.shopify.features.shoppingcart.viewmodel.factory.ShoppingCartListItemsViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

private const val TAG = "Exception"
class ShoppingCartFragment : Fragment(),CartOrderItemHandler,TotalAmountHandler {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var adapter: OrderItemsAdapter
    private lateinit var user: CustomerFirebase
    private val orders = mutableListOf<ModifyDraftOrderResponseLineItem>()
    private val shoppingCartListItemsViewModel by lazy {
        val remoteSource = ShoppingCartRemoteSourceImpl(RetrofitHelper.getInstance())
        val repo = ShoppingCartRepositoryImpl(remoteSource)
        val factory = ShoppingCartListItemsViewModelFactory(repo)
        ViewModelProvider(this,factory).get(ShoppingCartListItemsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_shopping_cart,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (binding.actvPromocoes as MaterialAutoCompleteTextView)
            .setSimpleItems(arrayOf("Item1","Item2","Item3","Item4","Item5"))
        adapter = OrderItemsAdapter(mutableListOf(),this,this,requireContext())
        binding.adapter = adapter

        shoppingCartListItemsViewModel.getShoppingCart("1127512539455")
        lifecycleScope.launch {
            shoppingCartListItemsViewModel.listItemsStateFlow.collectLatest{
                when(it){
                    is ApiState2.Loading -> {/*Do Nothing*/}
                    is ApiState2.Success -> {
                        /*
                        * Hide the loading progress indicator when the response is ready,
                        * save the order items in the remote shopping cart in a local in-memory list,
                        * set the initial total amount value using the orders' prices,
                        * and display the list in the orders recyclerview
                        * */
                        binding.indeterminateCircularProgressIndicator.visibility = View.GONE
                        orders.clear()
                        orders.addAll(it.data?.lineItems ?: mutableListOf())
                        adapter.submitList(orders)
                        adapter.notifyDataSetChanged()
                        setInitialTotalAmountValue()
                    }
                    else -> {
                        binding.indeterminateCircularProgressIndicator.visibility = View.GONE
                        showShoppingCartErrorDialog()
                    }
                }
            }
        }



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.navView.visibility = View.VISIBLE
    }

    private fun setInitialTotalAmountValue(){
        var total = 0.0
        for(order in orders){
            total += (order.price?.toDouble() ?: 0.0).times(order.requestedQuantity ?: 0)

        }
        binding.tvTotalAmountValue.text = "$total"
    }
    override fun removeOrder(order: ModifyDraftOrderResponseLineItem) {
        // TODO Remove the item from the draft order in the API
        orders.remove(order)
        modifyRemoteShoppingCart(null,-1)
    }

    /*
    * Increments the actual quantity of an order and modifies the remote draft order
    * */
    override fun incrementOrder(order: ModifyDraftOrderResponseLineItem,position: Int) {
        val incrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.plus(1))
        modifyRemoteShoppingCart(incrementedOrder,position)
    }

    override fun decrementOrder(order: ModifyDraftOrderResponseLineItem,position: Int) {
        val decrementedOrder = order.copy(requestedQuantity = order.requestedQuantity?.minus(1))
        modifyRemoteShoppingCart(decrementedOrder,position)
    }

    private fun modifyRemoteShoppingCart(order: ModifyDraftOrderResponseLineItem?,position: Int){
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
        shoppingCartListItemsViewModel.modifyShoppingCart("1127512539455",body)
    }

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

    private fun showConfirmationDialog(destinationId: Int){
        MaterialAlertDialogBuilder(requireContext(),R.style.MyDialogTheme)
            .setTitle("Save the state ?")
            .setMessage("Dou want to save the shopping cart state ?")
            .setNegativeButton(R.string.cancel){ dialog,_->
                dialog.dismiss()
            }
            .setPositiveButton("Save"){dialog,_->
                dialog.dismiss()
                findNavController().popBackStack(R.id.navigation_home, true)
                findNavController().navigate(destinationId)
            }
            .show()
    }


}
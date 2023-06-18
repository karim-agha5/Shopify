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
import com.example.shopify.R
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.getVariantOptions
import com.example.shopify.databinding.FragmentShoppingCartBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.core.common.features.draftorder.data.remote.ShoppingCartRemoteSourceImpl
import com.example.shopify.core.common.features.draftorder.data.ShoppingCartRepositoryImpl
import com.example.shopify.core.common.features.draftorder.model.Order
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartViewModel
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartViewModelFactory
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "Exception"
class ShoppingCartFragment : Fragment(),CartOrderItemHandler,TotalAmountHandler {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var adapter: OrderItemsAdapter
    private val orders = mutableListOf<Order>()
    private val shoppingCartViewModel by lazy {
        val remoteSource = ShoppingCartRemoteSourceImpl(RetrofitHelper.getInstance())
        val repo = ShoppingCartRepositoryImpl(remoteSource)
        val factory = ShoppingCartViewModelFactory(repo)
        ViewModelProvider(this,factory).get(ShoppingCartViewModel::class.java)
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

        // Create a draft
       /* lifecycleScope.launch(Dispatchers.Main) {
            val requestLineItems = listOf(CreateDraftOrderRequestLineItem("karim shoe","100",5))
            val customer = RequestCustomer(7062088548671)
            val draftOrder = CreateDraftOrderRequestDraftOrder(requestLineItems,null,customer)
            val body = CreateDraftOrderRequestBody(draftOrder)
            val response = repo.createShoppingCart(body)
            Log.i(TAG, "id = ${response.draftOrder.id}\nitem = ${response.draftOrder.line_items?.get(0)}")
        }*/


        // Get a draft
       /* lifecycleScope.launch(Dispatchers.Main) {
            val response = repo.getShoppingCart("1127521845567")
            Log.i(TAG, "id = ${response.draftOrder.id}\nname = ${response.draftOrder.lineItems?.get(0)?.name}")
        }*/

        // Modify a draft
       /* lifecycleScope.launch(Dispatchers.Main) {
            val lineItems = listOf(
                ModifyDraftOrderRequestLineItem(
                    58270954848575,
                    null,
                    null,
                    "Another Adidas shoessssss",
                    null,
                    1,
                    false,
                    appliedDiscount = null,
                    price = "500"
                ),
                ModifyDraftOrderRequestLineItem(
                    561512545512,
                    null,null,
                    "android studio shoe",
                    null,
                    3,
                    true,
                    appliedDiscount = null,
                    price = "1000"
                )
            )
            val draftOrder = ModifyDraftOrderRequestDraftOrder(
                null,
                "test@hotmail.com",
                true,
                lineItems,
                null,
            )
            val body = ModifyDraftOrderRequestBody(draftOrder)
            val response = repo.modifyShoppingCart("1127512539455",body)
            Log.i(TAG, "id = ${response.draftOrder.id}\nname = ${response.draftOrder.lineItems?.get(0)?.title}\n" +
                    "name = ${response.draftOrder.lineItems?.get(1)?.title}")
        }*/

        val response = shoppingCartViewModel.listItemsStateFlow
        lifecycleScope.launch(Dispatchers.Main){
            shoppingCartViewModel.getShoppingCart("1127512539455")
            var options: Pair<String,String>
            response.collect{
                for (item in it?.lineItems ?: listOf()){
                    options = getVariantOptions(item.variantTitle)
                    orders.add(
                        Order(
                            item.title,
                            options.second,
                            options.first,
                            "${item.quantity}",
                            item.price
                        )
                    )
                }
                orders.add(
                    Order(
                        "test",
                        "options.second",
                        "options.first1",
                        "5",
                        "55.0"
                    )
                )
                setInitialTotalAmountValue()
                adapter.submitList(orders)
                adapter.notifyItemRangeChanged(0,orders.size)
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
            total += order.orderItemPrice?.toDouble() ?: 0.0

        }
        binding.tvTotalAmountValue.text = "$total"
    }
    override fun removeOrder(order: Order) {
        // TODO Remove the item from the draft order in the API
        //orders.remove(order)
    }

    override fun adjustPrice(price: Double?) {
        binding
            .tvTotalAmountValue
            .text = "${binding.tvTotalAmountValue.text.toString().toDouble() + (price ?: 0.0)}"
    }
}
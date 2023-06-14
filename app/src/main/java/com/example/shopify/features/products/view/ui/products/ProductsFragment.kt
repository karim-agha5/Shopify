package com.example.shopify.features.products.view.ui.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.util.ApiState
import com.example.shopify.databinding.FragmentProductsBinding
import com.example.shopify.features.home.network.RetrofitClient
import com.example.shopify.features.home.repository.Repository
import com.example.shopify.features.home.view.ui.home.ProductsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    //private lateinit var viewModel: ProductsViewModel
    private val productViewModel by lazy {
        val productViewModelFactory =
            ProductsViewModelFactory(Repository.getInstance(RetrofitClient.getInstance())
            )
        ViewModelProvider(this,productViewModelFactory).get(ProductsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.productRec.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ProductsFragmentArgs = ProductsFragmentArgs.fromBundle(requireArguments())

        println("product fragment \n id received = ${args.recievedId}")
        println("brand title ${args.recievedTitle}")
        binding.productsScreenTitle.text = args.recievedTitle

        productViewModel.getProductsByCollection(args.recievedId)

        lifecycleScope.launch {
            productViewModel.products.collectLatest { state ->
                when (state) {
                    is ApiState.Success<*> -> {
                        val brands  = state.myData as List<Product>
                        initProducts(brands)
                    }

                    is ApiState.Failure -> {
                        showError()
                    }

                    else -> {
                        loadingUi()
                    }
                }
            }
        }

        binding.priceConstraint.setOnClickListener {
            makeSingleChoiceDialog(requireContext(), "Lowest-To-Highest", "Highest-To-Lowest")
        }

        binding.filtersConstraint.setOnClickListener {
            makeMultipleChoicesDialog(requireContext(), "T-Shirt", "Shoes", "Accessories")
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(),"Check your network connection",Toast.LENGTH_SHORT).show()
    }
    private fun initProducts(result : List<Product>){
        binding.productsProgress.visibility = View.GONE
        binding.productRec.adapter = ProductsAdapter(requireContext(),result,"USD",3.5f)
        binding.productRec.visibility = View.VISIBLE
    }

    private fun loadingUi() {
        binding.productsProgress.visibility = View.VISIBLE
        binding.productRec.visibility = View.INVISIBLE
    }

    private fun makeSingleChoiceDialog(context: Context, vararg options: String) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Arrange By")
            .setIcon(R.drawable.baseline_swap_vert_24)
            .setSingleChoiceItems(options, -1) { _, i ->
                println(options[i])
            }
            .setPositiveButton("OK") { _, _ ->
                println("Sorting")
            }
            .setNegativeButton("Cancel") { _, _ ->
                println("Canceled")
            }
            .create()
        dialog.show()
    }

    private fun makeMultipleChoicesDialog(context: Context, vararg options: String) {
        val checkedItems = BooleanArray(options.size) { false }

        AlertDialog.Builder(context)
            .setTitle("Filter By")
            .setIcon(R.drawable.baseline_filter_list_24)
            .setMultiChoiceItems(options, checkedItems) { _, i, isChecked ->
                if (isChecked) {
                    println("you checked ${options[i]}")
                } else {
                    println("you unChecked ${options[i]}")
                }
            }
            .setPositiveButton("OK") { _, _ ->
                val checkedOptions = mutableListOf<String>()
                for (i in options.indices) {
                    if (checkedItems[i]) {
                        checkedOptions.add(options[i])
                    }
                }
                println("filtering by $checkedOptions")
            }
            .setNegativeButton("Cancel") { _, _ ->
                println("Canceled")
            }
            .show()
    }

}
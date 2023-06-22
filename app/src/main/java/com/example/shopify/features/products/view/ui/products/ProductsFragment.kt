package com.example.shopify.features.products.view.ui.products

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.interfaces.OnProductClickListener
import com.example.shopify.core.util.ApiState
import com.example.shopify.databinding.FragmentProductsBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.products.network.ProductsClient
import com.example.shopify.features.products.repository.ProductsRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsFragment : Fragment(), OnProductClickListener {

    private lateinit var binding: FragmentProductsBinding

    private lateinit var productsAdapter : ProductsAdapter
    private val productViewModel by lazy {
        val productViewModelFactory =
            ProductsViewModelFactory(ProductsRepository.getInstance(ProductsClient.getInstance())
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
        productViewModel.getCollectionFilterOptions(args.recievedId)
        productsAdapter = ProductsAdapter(requireContext(), listOf(), this)
        binding.productRec.adapter = productsAdapter

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
            makeMultipleChoicesDialog(requireContext(), productViewModel.listOfOptions.toTypedArray() + "ALL")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)
        (activity as MainActivity).binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(),"Check your network connection",Toast.LENGTH_SHORT).show()
    }
    private fun initProducts(result : List<Product>){

        binding.productsProgress.visibility = View.GONE
        productsAdapter.updateList(result)
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
            .setSingleChoiceItems(options, -1) { _, _ -> }

        dialog.setPositiveButton("OK", null)
        dialog.setNegativeButton("Cancel") { _, _ ->
            println("Canceled")
        }

        val alertDialog = dialog.create()

        alertDialog.show()

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val selectedPosition = alertDialog.listView.checkedItemPosition
            if (selectedPosition == 0) {
                productViewModel.sortedProducts = productViewModel.sortedProducts.sortedBy { it.variants[0].price }
                productsAdapter.updateList(productViewModel.sortedProducts)
                binding.priceSelector.text = resources.getString(R.string.priceSelector_ascendingly)
            } else {
                productViewModel.sortedProducts = productViewModel.sortedProducts.sortedByDescending { it.variants[0].price }
                productsAdapter.updateList(productViewModel.sortedProducts)
                binding.priceSelector.text = resources.getString(R.string.priceSelector_descendingly)
            }
            alertDialog.dismiss()
        }
    }

    private fun makeMultipleChoicesDialog(context: Context, options: Array<String>) {
        val checkedItems = BooleanArray(options.size) { false }

        AlertDialog.Builder(context)
            .setTitle("Filter By")
            .setIcon(R.drawable.baseline_filter_list_24)
            .setMultiChoiceItems(options, checkedItems) { _, i, isChecked ->
                if (isChecked) {
                    println("You checked ${options[i]}")
                } else {
                    println("You unchecked ${options[i]}")
                }
            }
            .setPositiveButton("OK") { _, _ ->
                val selectedProductTypes = mutableListOf<String>()
                for (i in options.indices) {
                    if (checkedItems[i]) {
                        selectedProductTypes.add(options[i])
                    }
                }
                productViewModel.filterProductsByProductType(selectedProductTypes)
                binding.priceSelector.text =""
                productsAdapter.updateList(productViewModel.sortedProducts)
            }
            .setNegativeButton("Cancel") { _, _ ->
                println("Canceled")
            }
            .show()
    }

    override fun navigateToDetailsScreen(currentProduct: Product) {
        findNavController().navigate(
            ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(currentProduct)
        )
    }
}
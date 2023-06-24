package com.example.shopify.features.category.view.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomCollection
import com.example.shopify.core.util.ApiState
import com.example.shopify.databinding.FragmentCategoryBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.category.network.CategoryClient
import com.example.shopify.features.category.repository.CategoryRepository
import com.example.shopify.features.home.view.ui.home.OnCollectionSelected
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() , OnCollectionSelected{

    private lateinit var binding: FragmentCategoryBinding

    private val categoryViewModel by lazy {
        val categoryViewModelFactory = CategoryViewModelFactory(CategoryRepository.getInstance(CategoryClient.getInstance())
        )
        ViewModelProvider(this,categoryViewModelFactory).get(CategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            categoryViewModel.mainCategories.collectLatest { state ->
                when (state) {
                    is ApiState.Success<*> -> {
                        val categories  = state.myData as List<CustomCollection>
                        initCategories(categories)
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
        binding.categoryRec.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.toolbar.navigationIcon = null
        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.toolbar.findViewById<SearchView>(R.id.searchView).visibility = View.VISIBLE
    }
    private fun initCategories(result: List<CustomCollection>) {
        binding.categoryProgress.visibility = View.GONE
        binding.categoryRec.adapter = CategoryAdapter(requireContext(),result,this)
        binding.categoryRec.visibility = View.VISIBLE
    }

    private fun showError() {
        Toast.makeText(requireContext(),"Check Your Network Connection", Toast.LENGTH_SHORT).show()
        binding.categoryProgress.visibility = View.GONE
    }

    private fun loadingUi() {
        binding.categoryProgress.visibility = View.VISIBLE
        binding.categoryRec.visibility = View.INVISIBLE
    }

    override fun navigateToProductsScreen(collectionId: Long, collectionTitle: String) {
        findNavController().navigate(CategoryFragmentDirections.actionNavigationCategoriesToProductsFragment(collectionId,collectionTitle))
    }
}
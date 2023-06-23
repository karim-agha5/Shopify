package com.example.shopify.features.search.view

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.databinding.FragmentSearchBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.search.viewmodel.SearchViewModel
import com.example.shopify.features.search.viewmodel.SearchViewModelFactory


class SearchFragment : Fragment(), IOnSearchResultClickListener {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchResultsAdapter: SearchResultsAdapter
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var factory: SearchViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        factory = SearchViewModelFactory((activity as MainActivity).allProductsList)

        searchViewModel = ViewModelProvider(this,factory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        searchResultsAdapter = SearchResultsAdapter(this)
        binding.rvSearchResults.adapter = searchResultsAdapter
        searchResultsAdapter.submitProductsList(searchViewModel.filteredProductsLiveData.value?.toMutableList())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.tvCancelSearch.paintFlags = binding.tvCancelSearch.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.searchFragment = this
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()){
                    searchViewModel.filterProducts(newText)
                }
                return true
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.toolbar.visibility = View.GONE
        (activity as MainActivity).binding.linearLayout.setPadding(0)
    }

    fun navigateBack(view: View){
        findNavController().navigateUp()
    }

    override fun delegateProduct(product: Product) {
        TODO("Not yet implemented")
        //send it to product details
        //consider thinking about navigation and the back button
    }
}
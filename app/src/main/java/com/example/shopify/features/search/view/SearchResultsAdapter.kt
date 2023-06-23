package com.example.shopify.features.search.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.databinding.ProductDetailsImageCardBinding
import com.example.shopify.databinding.SearchProductNameCardBinding
import com.example.shopify.features.product_details.view.ProductImagesAdapter

class SearchResultsAdapter(
    private var onSearchClickListener: IOnSearchResultClickListener,
    private var products: MutableList<Product>? = mutableListOf()
) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {
    private val TAG = "SearchResultsAdapter"
    inner class ViewHolder(val searchProductNameCardBinding: SearchProductNameCardBinding) :
        RecyclerView.ViewHolder(searchProductNameCardBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultsAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<SearchProductNameCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.search_product_name_card,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.searchProductNameCardBinding.tvSearchResult.text = products?.get(position)?.title
        holder.searchProductNameCardBinding.searchTextCard.setOnClickListener {
            onSearchClickListener.delegateProduct(products!!.get(position))
        }
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    fun submitProductsList(products: MutableList<Product>?){
        Log.d(TAG, "submitProductsList: ")
        this.products = products
        notifyDataSetChanged()
    }
}

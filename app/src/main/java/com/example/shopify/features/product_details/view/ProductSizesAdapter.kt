package com.example.shopify.features.product_details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.databinding.ProductDetailsImageCardBinding
import com.example.shopify.databinding.ProductSizeCardBinding

class ProductSizesAdapter: RecyclerView.Adapter<ProductSizesAdapter.ViewHolder>() {


    inner class ViewHolder(val productCardSizeBinding: ProductSizeCardBinding) :
        RecyclerView.ViewHolder(productCardSizeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ProductSizeCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.product_size_card,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}
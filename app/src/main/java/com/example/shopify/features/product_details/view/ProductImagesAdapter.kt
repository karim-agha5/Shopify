package com.example.shopify.features.product_details.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.databinding.ProductDetailsImageCardBinding

class ProductImagesAdapter :
    ListAdapter<Product, ProductImagesAdapter.ViewHolder>(DiffUtilProductImage()) {
    private val TAG = "ProductImagesAdapter"
    private var selectedImage = 0
    private var ctr = 1

    inner class ViewHolder(var productCardBinding: ProductDetailsImageCardBinding) :
        RecyclerView.ViewHolder(productCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_details_image_card,
            parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentProduct = getItem(position)
        if(selectedImage == position){
            Log.d(TAG, "onBindViewHolder: up")
            holder.productCardBinding.productDetailsImageCard.strokeColor =
                ContextCompat.getColor(holder.productCardBinding.root.context, R.color.primaryRed)
        }else{
            Log.d(TAG, "onBindViewHolder: donw")/*
            holder.productCardBinding.productDetailsImageCard.strokeColor =
                ContextCompat.getColor(holder.productCardBinding.root.context, R.color.black)*/
        }

        holder.productCardBinding.mvProductImage.setImageResource(R.drawable.bag)
        holder.productCardBinding.productDetailsImageCard.setOnClickListener {
            if(position != selectedImage){
                selectedImage = position
                submitList(currentList)
                Log.d(TAG, "onBindViewHolder: here++")
            }
            /*if(ctr > 1){
                holder.productCardBinding.productDetailsImageCard.strokeColor =
                    ContextCompat.getColor(holder.productCardBinding.root.context, R.color.white)
                ctr--
                Log.d(TAG, "onBindViewHolder: here--")
            }else if (holder.productCardBinding.productDetailsImageCard.strokeColor == ContextCompat.getColor(
                    holder.productCardBinding.root.context,
                    R.color.white
                )
            ) {
                Log.d(TAG, "onBindViewHolder: here elseif")
                holder.productCardBinding.productDetailsImageCard.strokeColor =
                    ContextCompat.getColor(holder.productCardBinding.root.context, R.color.primaryRed)
                selectedImage = position
                submitList(currentList)
            }*/

        }
    }
}
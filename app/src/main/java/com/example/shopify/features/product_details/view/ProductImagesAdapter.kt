package com.example.shopify.features.product_details.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shopify.R
import com.example.shopify.core.common.data.model.ImageX
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.databinding.ProductDetailsImageCardBinding
import com.example.shopify.features.product_details.interfaces.OnImageCardClickListener
import com.example.shopify.features.product_details.interfaces.OnImageSlideListener

class ProductImagesAdapter(
    private var context: Context,
    private var productImgs: List<ImageX>,
    private var onImageCardClickListener: OnImageCardClickListener,
    private var productList: MutableList<Product> = mutableListOf()
) : RecyclerView.Adapter<ProductImagesAdapter.ViewHolder>(), OnImageSlideListener {
    private val TAG = "ProductImagesAdapter"
    private var selectedImage = 0

    override var imageIndex: Int = 0
        get() = field //the old value which at the starts it equals zero because it's initialized with it
        set(value) {//customer setter takes a new value
            selectedImage = value //first assign it to the selectedImage
            notifyDataSetChanged() //notify so it rebind
            field = value //assign imageIndex with the new value
        }

    inner class ViewHolder(val productCardBinding: ProductDetailsImageCardBinding) :
        RecyclerView.ViewHolder(productCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ProductDetailsImageCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.product_details_image_card,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
//        Log.d(TAG, "onBindViewHolder: $selectedImage")

        if (selectedImage == position) {
            Log.d(TAG, "onBindViewHolder: up")
            holder.productCardBinding.productDetailsImageCard.strokeColor =
                ContextCompat.getColor(holder.productCardBinding.root.context, R.color.primaryRed)
        } else {
            Log.d(TAG, "onBindViewHolder: down")
            holder.productCardBinding.productDetailsImageCard.strokeColor =
                ContextCompat.getColor(holder.productCardBinding.root.context, R.color.textGray)
        }

        Glide.with(context)
            .load(productImgs[position].src)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache image for better performance
            .placeholder(R.mipmap.placeholder)
            .error(R.mipmap.placeholder)
            .into(holder.productCardBinding.mvProductImage)


        holder.productCardBinding.productDetailsImageCard.setOnClickListener {
            if (position != selectedImage) {
                selectedImage = position
                notifyDataSetChanged()
                Log.d(TAG, "onBindViewHolder: here++")
            }
            onImageCardClickListener.cardIndex=  position
        }
    }

    override fun getItemCount(): Int {
        // Return the size of the list of products
        return productImgs.size
    }
}
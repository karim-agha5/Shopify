package com.example.shopify.features.product_details.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.databinding.ProductDetailsImageCardBinding

class ProductImagesAdapter(private var productList: MutableList<Product> = mutableListOf()) : RecyclerView.Adapter<ProductImagesAdapter.ViewHolder>() {
    private val TAG = "ProductImagesAdapter"
    private var selectedImage = 0


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
        Log.d(TAG, "onBindViewHolder: $selectedImage")
        val currentProduct = productList[position]

        if (selectedImage == position) {
            Log.d(TAG, "onBindViewHolder: up")
            holder.productCardBinding.productDetailsImageCard.strokeColor =
                ContextCompat.getColor(holder.productCardBinding.root.context, R.color.primaryRed)
        } else {
            Log.d(TAG, "onBindViewHolder: down")
            holder.productCardBinding.productDetailsImageCard.strokeColor =
                ContextCompat.getColor(holder.productCardBinding.root.context, R.color.textGray)
        }

        holder.productCardBinding.mvProductImage.setImageResource(R.drawable.bag)
        holder.productCardBinding.productDetailsImageCard.setOnClickListener {
            if (position != selectedImage) {
                selectedImage = position
                notifyDataSetChanged()
                Log.d(TAG, "onBindViewHolder: here++")
            }
        }
    }

    override fun getItemCount(): Int {
        // Return the size of the list of products
        return productList.size
    }

    fun setProducts(products: MutableList<Product>) {
        productList = products
        notifyDataSetChanged()
    }
}

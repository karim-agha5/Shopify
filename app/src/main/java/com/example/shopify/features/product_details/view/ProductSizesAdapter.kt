package com.example.shopify.features.product_details.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.databinding.ProductDetailsImageCardBinding
import com.example.shopify.databinding.ProductSizeCardBinding
import com.example.shopify.features.product_details.interfaces.OnVariantSelection

class ProductSizesAdapter(
    private var variantTitle: String,
    private var values: List<String>,
    private var onVariantSelection: OnVariantSelection
) : RecyclerView.Adapter<ProductSizesAdapter.ViewHolder>() {
    private val TAG = "ProductSizesAdapter"
    private var selectedSize = 0

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

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        if (selectedSize == position) {
            Log.d(TAG, "onBindViewHolder: up")
            holder.productCardSizeBinding.productSizeCard.strokeColor =
                ContextCompat.getColor(
                    holder.productCardSizeBinding.root.context,
                    R.color.lightPrimaryRed
                )

            holder.productCardSizeBinding.productSizeCard.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.productCardSizeBinding.root.context,
                    R.color.lightPrimaryRed
                )
            )
        }else{
            Log.d(TAG, "onBindViewHolder: down")
            holder.productCardSizeBinding.productSizeCard.strokeColor =
                ContextCompat.getColor(
                    holder.productCardSizeBinding.root.context,
                    R.color.textGray
                )

            holder.productCardSizeBinding.productSizeCard.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.productCardSizeBinding.root.context,
                    R.color.white
                )
            )
        }

        holder.productCardSizeBinding.tvProductSize.text =
            "${values.get(position)} / ${variantTitle.substringAfter("/").trim()}"

        holder.productCardSizeBinding.productSizeCard.setOnClickListener {
            if(position != selectedSize){
                onVariantSelection.variantIndex = position
                selectedSize = position
                notifyDataSetChanged()
                Log.d(TAG, "onBindViewHolder: click")
            }
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
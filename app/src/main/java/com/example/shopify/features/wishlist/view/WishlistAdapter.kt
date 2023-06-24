package com.example.shopify.features.wishlist.view

import android.content.Context
import com.example.shopify.features.search.view.IOnSearchResultClickListener
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.databinding.ProductDetailsImageCardBinding
import com.example.shopify.databinding.SearchProductNameCardBinding
import com.example.shopify.databinding.WishlistCardBinding
import com.example.shopify.features.product_details.view.ProductImagesAdapter

class WishlistAdapter(
    private var context: Context,
    private var onFavoriteClickListener: IOnFavoriteClickListener,
    private var products: MutableList<Product>? = mutableListOf()
) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    private val TAG = "SearchResultsAdapter"

    inner class ViewHolder(val wishlistCardBinding: WishlistCardBinding) :
        RecyclerView.ViewHolder(wishlistCardBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishlistAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<WishlistCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.wishlist_card,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wishlistCardBinding.tvFavTitle.text = products!![position].title

        Glide.with(context)
            .load(products!![position].image.src)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache image for better performance
            .placeholder(R.mipmap.placeholder)
            .error(R.mipmap.placeholder)
            .into(holder.wishlistCardBinding.favPhoto)

        holder.wishlistCardBinding.tvFavPrice.text = "$ {$products!![position].variants.first().price.toString()}"
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

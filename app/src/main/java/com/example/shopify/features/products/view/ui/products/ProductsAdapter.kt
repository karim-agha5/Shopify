package com.example.shopify.features.products.view.ui.products

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.interfaces.OnProductClickListener
import com.example.shopify.databinding.ProductCardBinding
import com.example.shopify.features.home.view.ui.home.OnFavoriteClickListener
import java.util.Random

class ProductsAdapter(
    private val context: Context,
    private var products: List<Product>,
    private val onProductClickListener: OnProductClickListener,
    private val onFavoriteClickListener: OnFavoriteClickListener
) :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {
    private var favsRealList: MutableList<Product> = mutableListOf()

    class MyViewHolder(var binding: ProductCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ProductCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateList(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    //    TODO adding the currency functionality
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        assignRatingToProduct(products[position])
        holder.binding.bindingProduct = products[position]
        holder.binding.bindingRating = products[position].rating
        Glide.with(context).load(products[position].image?.src).into(holder.binding.productImage)
        Log.d("TAG", "onBindViewHolder: Before ${favsRealList.size }")

        if (favsRealList.contains(products[position])) {
            holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_filled)
        } else {
            holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_48px)
        }
        holder.binding.productIsFavImage.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: ${products[position].isFav}")
            if (!favsRealList.contains(products[position])) {
                holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_filled)
                products[position].isFav = true
                onFavoriteClickListener.addToFavs(products[position])
                Log.d("TAG", "onBindViewHolder: added")
            } else {
                holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_48px)
                products[position].isFav = false
                onFavoriteClickListener.removeFromFavs(products[position])
                Log.d("TAG", "onBindViewHolder: removed")
            }
        }

        holder.binding.productCard.setOnClickListener {
            onProductClickListener.navigateToDetailsScreen(products[position])
        }
    }

    fun addRealFavs(favs: MutableList<Product>){
        favsRealList = favs
        notifyDataSetChanged()
    }
    private fun assignRatingToProduct(product: Product) {
        val sharedPreferences = context.getSharedPreferences("ProductRatings", Context.MODE_PRIVATE)

        val rating = sharedPreferences.getFloat(product.id.toString(), 0f)
        if (rating == 0f) {
            val newRating = assignRandomRating()
            product.rating = newRating
            sharedPreferences.edit().putFloat(product.id.toString(), newRating.toFloat()).apply()
        } else {
            product.rating = String.format("%.2f", rating)
        }
    }

    private fun assignRandomRating(): String {
        val random = Random()
        val rating = (random.nextDouble() * 4.0) + 1.0
        return String.format("%.2f", rating)
    }

}
package com.example.shopify.features.home.view.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.databinding.ProductCardBinding

class ProductsAdapter(private val products: List<ProductMock>) :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {


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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.productName.text = products[position].name
        holder.binding.productImage.setImageResource(products[position].img)
        holder.binding.productPrice.text = products[position].price
        holder.binding.productRating.text = products[position].rating.toString()
        if (products[position].isFav) {
            holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_filled)
        } else {
            holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_48px)
        }
        holder.binding.productIsFavImage.setOnClickListener {
            if (!products[position].isFav){
                holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_filled)
                products[position].isFav = true
            } else {
                holder.binding.productIsFavImage.setImageResource(R.drawable.favorite_48px)
                products[position].isFav = false
            }
        }

    }
}
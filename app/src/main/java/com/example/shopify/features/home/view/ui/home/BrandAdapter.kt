package com.example.shopify.features.home.view.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.core.common.data.model.SmartCollection
import com.example.shopify.databinding.BrandCardBinding


class BrandAdapter (private val context: Context, private val brands: List<SmartCollection>) :
    RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {


    class MyViewHolder(var binding: BrandCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = BrandCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return brands.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(brands[position].image.src).into(holder.binding.brandImg)
    }
}

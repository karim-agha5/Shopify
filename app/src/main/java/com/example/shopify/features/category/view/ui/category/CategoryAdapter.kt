package com.example.shopify.features.category.view.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomCollection
import com.example.shopify.databinding.CategoryCardBinding
import com.example.shopify.features.home.view.ui.home.OnCollectionSelected

class CategoryAdapter(
    private val context: Context,
    private var categories: List<CustomCollection>,
    private val onCategorySelected: OnCollectionSelected
) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: CategoryCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = CategoryCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.bindingCategory = categories[position]
        holder.binding.action = onCategorySelected
        Glide.with(context).load(categories[position].image?.src)
            .error(R.drawable.category_placeholder)
            .into(holder.binding.categoryImage)

    }
}
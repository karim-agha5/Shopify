package com.example.shopify.features.home.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shopify.core.common.interfaces.RecyclerViewItemClickListener
import com.example.shopify.databinding.AdItemLayoutBinding

class AdImagesAdapter(
    private var imageList: MutableList<Int>,
    private val viewPager: ViewPager2,
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<AdImagesAdapter.AdImagesViewHolder>(){

    private lateinit var binding: AdItemLayoutBinding

    inner class AdImagesViewHolder(binding: AdItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val ivAdImage: ImageView = binding.ivAdImageItem
       /* init {
            binding.root.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }*/
    }

    private val runnableInsideAdapter = Runnable{
        val temp = mutableListOf<Int>().apply {
            addAll(imageList)
        }
        imageList.clear()
        imageList.addAll(temp)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdImagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AdItemLayoutBinding.inflate(inflater,parent,false)
        return AdImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdImagesViewHolder, projectedPosition: Int) {

        val position = projectedPosition % imageList.size
        holder.ivAdImage.setImageResource(imageList[position])
        if(position == (imageList.size - 1)){
            viewPager.post(runnableInsideAdapter)
        }
        binding.root.setOnClickListener {
            listener.onItemClicked(position)
            Log.i("Exception",imageList[position].toString())
        }

    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }
}
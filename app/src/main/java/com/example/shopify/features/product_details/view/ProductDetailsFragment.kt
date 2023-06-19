package com.example.shopify.features.product_details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopify.R
import com.example.shopify.core.common.data.model.ImageX
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.Variant
import com.example.shopify.databinding.FragmentProductDetailsBinding
import com.example.shopify.features.MainActivity


class ProductDetailsFragment : Fragment() {
    private val TAG = "ProductDetailsFragment"
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter
    private var imgs: MutableList<SlideModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

        imgs.add(SlideModel(R.drawable.ad_image_1_sale))
        imgs.add(SlideModel(R.drawable.ad_image_2_coupon))
        imgs.add(SlideModel(R.drawable.ad_image_3_coupon))
        imgs.add(SlideModel(R.drawable.ad_image_4_coupon))
        binding.imageSlider.setImageList(imgs,ScaleTypes.FIT)
        binding.imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)


        productImagesAdapter = ProductImagesAdapter()
        binding.rvImages.adapter = productImagesAdapter
        productImagesAdapter.submitList(mutableListOf())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.button.setOnClickListener {
            Log.d(TAG, "onViewCreated: clicked")
            binding.imageSlider.startSliding()
            binding.imageSlider.setItemChangeListener(object : ItemChangeListener {
                override fun onItemChanged(position: Int) {
                    Log.d(TAG, "onItemChanged: $position")
                    if(position == 2){
                    binding.imageSlider.stopSliding()
                    }
                }
            })
        }*/
        productImagesAdapter.submitList(mutableListOf(Product(
            id = 12345,
            image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
            images = listOf(),
            options = listOf(),
            product_type = "Clothing",
            status = "Available",
            tags = "shirt, summer, casual",
            title = "Example T-Shirt",
            variants = listOf(),
            vendor = "Example Vendor",
            isFav = false
        ),
            Product(
                id = 12345,
                image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
                images = listOf(),
                options = listOf(),
                product_type = "Clothing",
                status = "Available",
                tags = "shirt, summer, casual",
                title = "Example T-Shirt",
                variants = listOf(),
                vendor = "Example Vendor",
                isFav = false
            ),
            Product(
                id = 12345,
                image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
                images = listOf(),
                options = listOf(),
                product_type = "Clothing",
                status = "Available",
                tags = "shirt, summer, casual",
                title = "Example T-Shirt",
                variants = listOf(),
                vendor = "Example Vendor",
                isFav = false
            ),
            Product(
                id = 12345,
                image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
                images = listOf(),
                options = listOf(),
                product_type = "Clothing",
                status = "Available",
                tags = "shirt, summer, casual",
                title = "Example T-Shirt",
                variants = listOf(),
                vendor = "Example Vendor",
                isFav = false
            ),
            Product(
                id = 12345,
                image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
                images = listOf(),
                options = listOf(),
                product_type = "Clothing",
                status = "Available",
                tags = "shirt, summer, casual",
                title = "Example T-Shirt",
                variants = listOf(),
                vendor = "Example Vendor",
                isFav = false
            ),
            Product(
                id = 12345,
                image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
                images = listOf(),
                options = listOf(),
                product_type = "Clothing",
                status = "Available",
                tags = "shirt, summer, casual",
                title = "Example T-Shirt",
                variants = listOf(),
                vendor = "Example Vendor",
                isFav = false
            ),
            Product(
                id = 12345,
                image = ImageX(4,4,4,4,"fda","dfa", listOf(),311),
                images = listOf(),
                options = listOf(),
                product_type = "Clothing",
                status = "Available",
                tags = "shirt, summer, casual",
                title = "Example T-Shirt",
                variants = listOf(),
                vendor = "Example Vendor",
                isFav = false
            ),
        )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO to think about padding when nav back it will stay zero?
        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.navView.visibility = View.GONE
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)
    }
}
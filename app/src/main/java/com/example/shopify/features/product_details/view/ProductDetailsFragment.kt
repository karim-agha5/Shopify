package com.example.shopify.features.product_details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopify.R
import com.example.shopify.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private var imgs: MutableList<SlideModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

        imgs.add(SlideModel(R.drawable.ad_image_1_sale,"img1"))
        imgs.add(SlideModel(R.drawable.ad_image_2_coupon,"img2"))
        imgs.add(SlideModel(R.drawable.ad_image_3_coupon,"img3"))
        imgs.add(SlideModel(R.drawable.ad_image_4_coupon,"img4"))
        binding.imageSlider.setImageList(imgs,ScaleTypes.CENTER_CROP)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
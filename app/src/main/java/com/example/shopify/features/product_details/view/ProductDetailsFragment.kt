package com.example.shopify.features.product_details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopify.R
import com.example.shopify.databinding.FragmentProductDetailsBinding
import com.example.shopify.features.MainActivity


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
        binding.imageSlider.setImageList(imgs,ScaleTypes.FIT)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO to think about padding when nav back it will stay zero?
        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.navView.visibility = View.GONE
    }
}
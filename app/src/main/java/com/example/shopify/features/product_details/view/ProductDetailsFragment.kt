package com.example.shopify.features.product_details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopify.R
import com.example.shopify.core.common.data.model.ImageX
import com.example.shopify.core.common.data.model.Option
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.Variant
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.remote.DraftOrderRemoteSourceImpl
import com.example.shopify.databinding.FragmentProductDetailsBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModel
import com.example.shopify.features.product_details.interfaces.OnImageCardClickListener
import com.example.shopify.features.product_details.interfaces.OnVariantSelection
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModel
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModelFactory


class ProductDetailsFragment : Fragment(), OnImageCardClickListener, OnVariantSelection {
    private val TAG = "ProductDetailsFragment"
    private var isCardClicked = false
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter
    private lateinit var productSizesAdapter: ProductSizesAdapter
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var factory: ProductDetailsViewModelFactory

    private val args by navArgs<ProductDetailsFragmentArgs>()
    private var imgs: MutableList<SlideModel> = mutableListOf()


    override var cardIndex: Int = 0
        get() = field
        set(value) {
            isCardClicked = true
            binding.imageSlider.startSliding()
            field = value
        }
    override var variantIndex: Int = 0
        get() = field
        set(value) {
            args.productArgs.selectedVariantIndex = value.toLong()
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

        factory = ProductDetailsViewModelFactory(
            (activity as MainActivity).customerInfo?.cartId!!,
            DraftOrderRepositoryImpl(DraftOrderRemoteSourceImpl(RetrofitHelper.getInstance()))
        )

        productDetailsViewModel = ViewModelProvider(this, factory).get(ProductDetailsViewModel::class.java)

        args.productArgs.images.forEachIndexed { index, image ->
            imgs.add(SlideModel(args.productArgs.images[index].src))
        }

        binding.imageSlider.setImageList(imgs, ScaleTypes.FIT)
        binding.imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)

        binding.imageSlider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                Log.d(TAG, "onItemChanged: $position")
                if (position == cardIndex) {
                    binding.imageSlider.stopSliding()
                    isCardClicked = false
                } else if (!isCardClicked) {
                    productImagesAdapter.imageIndex = position
                }
            }
        })

        productImagesAdapter = ProductImagesAdapter(requireContext(), args.productArgs.images, this)
        binding.rvImages.adapter = productImagesAdapter

        productSizesAdapter = ProductSizesAdapter(
            args.productArgs.variants.first().title,
            args.productArgs.options.first().values,
            this
        )
        binding.rvSizes.adapter = productSizesAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvProductTitle.text = args.productArgs.title

        binding.tvProductPrice.text = "$" + args.productArgs.variants.first().price.toString()

        binding.btnAdd.setOnClickListener {
            Log.d(TAG, "onViewCreated: hi")
            updateCounterAndPrice(1)
        }

        binding.btnMinus.setOnClickListener {
            updateCounterAndPrice(-1)
        }

        binding.btnAddToCart.setOnClickListener {
            productDetailsViewModel.addToCart(args.productArgs){
                if(it){
                    Log.d(TAG, "onViewCreated: OH MY GOD!!")
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.navView.visibility = View.GONE
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)

        (activity as MainActivity).binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateCounterAndPrice(num: Int) {
        val currentCounter = binding.tvCounter.text.toString().toInt()
        val newCounter = currentCounter + num

        if (newCounter in 1..args.productArgs.variants.size) {
            binding.tvCounter.text = newCounter.toString()
        }
    }
}
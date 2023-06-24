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
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopify.R
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.remote.DraftOrderRemoteSourceImpl
import com.example.shopify.databinding.FragmentProductDetailsBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.product_details.interfaces.OnImageCardClickListener
import com.example.shopify.features.product_details.interfaces.OnVariantSelection
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModel
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModelFactory
import android.app.AlertDialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProductDetailsFragment : Fragment(), OnImageCardClickListener, OnVariantSelection {
    private val TAG = "ProductDetailsFragment"
    private var isCardClicked = false
    private var auth: FirebaseAuth
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

    init {
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args.productArgs.selectedVariantIndex = 0

        binding = FragmentProductDetailsBinding.inflate(inflater)

        factory = ProductDetailsViewModelFactory(
            (activity as MainActivity).customerInfo?.cartId,
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
            if(auth.currentUser != null){
                binding.btnAddToCart.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE

                productDetailsViewModel.setProductCounter(binding.tvCounter.text.toString())
                productDetailsViewModel.addToCart(args.productArgs){
                    if(it){
                        Log.d(TAG, "onViewCreated: OH MY GOD!!")
                        binding.btnAddToCart.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE

                        showDialog(true, requireContext())
                    }
                }
            }else{
                showDialog(false, requireContext())
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.navView.visibility = View.GONE
        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)

        (activity as MainActivity).binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateCounterAndPrice(num: Int) {//num = 1 or -1
        val variantsSize = args.productArgs.variants[variantIndex].inventory_quantity
        val currentCounter = binding.tvCounter.text.toString().toInt()
        val newCounter = currentCounter + num

        if (newCounter in 1 .. variantsSize) {
            binding.tvCounter.text = newCounter.toString()
        }


    }

    private fun showDialog(isAuthorizedUser: Boolean, context: Context) {
        if(isAuthorizedUser){
            val customInflater = layoutInflater.inflate(R.layout.dialog_success, null)
            val title: TextView = customInflater.findViewById(R.id.tv_product_dialog_title)
            val btn: Button = customInflater.findViewById(R.id.btn_continue)
            val dialog = MaterialAlertDialogBuilder(context, R.style.MyDialogTheme)
                .setView(customInflater)
                .setCancelable(true)
                .show()

            title.text = args.productArgs.title
            btn.setOnClickListener {
                dialog.dismiss()
            }
        }else{
            val customInflater = layoutInflater.inflate(R.layout.dialog_login, null)
            val btnLogin: Button = customInflater.findViewById(R.id.btn_login)
            val btnRegister: Button = customInflater.findViewById(R.id.btn_register)
            val dialog = MaterialAlertDialogBuilder(context, R.style.MyDialogTheme)
                .setView(customInflater)
                .setCancelable(true)
                .show()

            btnLogin.setOnClickListener {
                dialog.dismiss()
                findNavController().navigate(R.id.action_productDetailsFragment_to_loginFragment)
            }
            btnRegister.setOnClickListener{
                dialog.dismiss()
                findNavController().navigate(R.id.action_productDetailsFragment_to_registrationFragment)
            }
        }

    }


}
package com.example.shopify.features.product_details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
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
import com.example.shopify.databinding.FragmentProductDetailsBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.product_details.interfaces.OnImageCardClickListener


class ProductDetailsFragment : Fragment(), OnImageCardClickListener {
    private val TAG = "ProductDetailsFragment"
    private var isCardClicked = false
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter
    private lateinit var productSizesAdapter: ProductSizesAdapter

    private val args by navArgs<ProductDetailsFragmentArgs>()

    /*private var product = Product(
        id = 12345,
        image = ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/7883dc186e15bf29dad696e1e989e914.jpg?v=1685529312","dfa", listOf(),311),
        images = listOf(
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/7883dc186e15bf29dad696e1e989e914.jpg?v=1685529312","dfa", listOf(),311),
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/8cd561824439482e3cea5ba8e3a6e2f6.jpg?v=1685529312","dfa", listOf(),311),
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/2e1f72987692d2dcc3c02be2f194d6c5.jpg?v=1685529312","dfa", listOf(),311),
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/6216e82660d881e6f2b0e46dc3f8844a.jpg?v=1685529312","dfa", listOf(),311)
        ),
        options = listOf(
            Option(
                id = 10686356521279,
                product_id = 8412090335551,
                name = "Size",
                position = 1,
                values = listOf("8", "9", "10", "13")
            )
        ),
        product_type = "Clothing",
        status = "Available",
        tags = "shirt, summer, casual",
        title = "ADIDAS | KID'S STAN SMITH",
        variants = listOf(
            Variant(
                fulfillment_service = "manual",
                grams = 0,
                id = 45183224021311,
                image_id = 431,
                inventory_item_id = 47229515137343,
                inventory_management = "shopify",
                inventory_policy = "deny",
                inventory_quantity = 17,
                old_inventory_quantity = 17,
                option1 = "8",
                option2 = "blue",
                option3 = "",
                position = 1,
                price = 119.95,
                product_id = 8412090335551,
                requires_shipping = true,
                sku = "VN-04-blue-8",
                taxable = true,
                title = "8 / blue",
                updated_at = "2023-05-31T06:35:40-04:00",
                weight = 0.0,
                weight_unit = "kg"
            )
        ),
        vendor = "ADIDAS",
        isFav = false
    )*/
    private var imgs: MutableList<SlideModel> = mutableListOf()


    override var cardIndex: Int = 0
        get() = field
        set(value) {
            isCardClicked = true
            binding.imageSlider.startSliding()
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

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
            args.productArgs.options.first().values
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
//            binding.tvProductPrice.text = "$${args.productArgs.variants.first().price * newCounter}"
        }
    }

}
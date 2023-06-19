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
import com.example.shopify.features.product_details.interfaces.OnImageCardClickListener


class ProductDetailsFragment : Fragment(), OnImageCardClickListener {
    private val TAG = "ProductDetailsFragment"
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter
    private var product = Product(
        id = 12345,
        image = ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/7883dc186e15bf29dad696e1e989e914.jpg?v=1685529312","dfa", listOf(),311),
        images = listOf(
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/7883dc186e15bf29dad696e1e989e914.jpg?v=1685529312","dfa", listOf(),311),
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/8cd561824439482e3cea5ba8e3a6e2f6.jpg?v=1685529312","dfa", listOf(),311),
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/2e1f72987692d2dcc3c02be2f194d6c5.jpg?v=1685529312","dfa", listOf(),311),
            ImageX(4,4,4,4,"https://cdn.shopify.com/s/files/1/0769/7635/7695/products/6216e82660d881e6f2b0e46dc3f8844a.jpg?v=1685529312","dfa", listOf(),311)
        ),
        options = listOf(),
        product_type = "Clothing",
        status = "Available",
        tags = "shirt, summer, casual",
        title = "ADIDAS | KID'S STAN SMITH",
        variants = listOf(),
        vendor = "ADIDAS",
        isFav = false
    )
    private var imgs: MutableList<SlideModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

        product.images.forEachIndexed { index, image ->
            imgs.add(SlideModel(product.images[index].src))
        }

        /*imgs.add(SlideModel(R.drawable.ad_image_1_sale))
        imgs.add(SlideModel(R.drawable.ad_image_2_coupon))
        imgs.add(SlideModel(R.drawable.ad_image_3_coupon))
        imgs.add(SlideModel(R.drawable.ad_image_4_coupon))*/
        binding.imageSlider.setImageList(imgs,ScaleTypes.FIT)
        binding.imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)

        binding.imageSlider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                Log.d(TAG, "onItemChanged: $position")
                if(position == cardIndex){
                    binding.imageSlider.stopSliding()
                }
            }
        })

        productImagesAdapter = ProductImagesAdapter(requireContext(), product.images,this)
        binding.rvImages.adapter = productImagesAdapter

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

        /*productImagesAdapter.setProducts(mutableListOf(Product(
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
        )*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO to think about padding when nav back it will stay zero?
        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.navView.visibility = View.GONE
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)
    }

    override var cardIndex: Int = 0
        get() = field
        set(value) {
            binding.imageSlider.startSliding()
            field = value
        }


}
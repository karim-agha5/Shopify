package com.example.shopify.features.home.view.ui.home

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.Promocode
import com.example.shopify.core.common.data.model.SmartCollection
import com.example.shopify.core.common.interfaces.RecyclerViewItemClickListener
import com.example.shopify.core.util.ApiState
import com.example.shopify.core.util.Constants
import com.example.shopify.databinding.FragmentHomeBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.home.network.HomeClient
import com.example.shopify.features.home.repository.HomeRepository
import com.example.shopify.features.home.view.AdImagesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), RecyclerViewItemClickListener, OnCollectionSelected, OnProductClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adImagesAdapter: AdImagesAdapter
    private var handler: Handler = Handler(Looper.myLooper()!!)
    private var imageList = ArrayList<Int>()
    private val homeViewModel by lazy {
        val homeViewModelFactory = HomeViewModelFactory(
            HomeRepository.getInstance(HomeClient.getInstance())
        )
        ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        lifecycleScope.launch {
            homeViewModel.brands.collectLatest { state ->
                when (state) {
                    is ApiState.Success<*> -> {
                        val brands  = state.myData as List<SmartCollection>
                        initBrands(brands)
                    }

                    is ApiState.Failure -> {
                        showError()
                    }

                    else -> {
                        loadingUi()
                    }
                }
            }
        }

        lifecycleScope.launch {
            homeViewModel.tenProducts.collectLatest { state ->
                when (state) {
                    is ApiState.Success<*> -> {
                        val products = state.myData as List<Product>
                        initProducts(products)
                    }

                    is ApiState.Failure -> {
                        showError()
                    }

                    else -> {
                        loadingUi()
                    }
                }
            }
        }

        binding.productsRecView.layoutManager = GridLayoutManager(requireContext(), 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  (activity as MainActivity).window.statusBarColor = resources.getColor(R.color.backgroundGray)

        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.linearLayout.setPadding(16)*/

        binding.brandRecView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        addImagesToList()
        setupAdImagesViewPager()
        setupAdImageTransformation()
        setupAdImagesViewPagerCallback()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).window.statusBarColor = resources.getColor(R.color.backgroundGray)

        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.linearLayout.setPadding(16)
    }

    private fun addImagesToList() {
        imageList.add(R.drawable.ad_image_1_sale)
        imageList.add(R.drawable.ad_image_2_coupon)
        imageList.add(R.drawable.ad_image_3_coupon)
        imageList.add(R.drawable.ad_image_4_coupon)
    }

    private fun setupAdImagesViewPager() {
        adImagesAdapter = AdImagesAdapter(imageList, this)
        binding.vpAds.adapter = adImagesAdapter
        binding.vpAds.offscreenPageLimit = 4
        binding.vpAds.clipChildren = false
        binding.vpAds.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.vpAds.isUserInputEnabled = false
    }

    // Transition animation between ad pages in the viewpager
    private fun setupAdImageTransformation() {
        binding.vpAds.setPageTransformer { page, _ ->
            page.alpha = 0f
            page.visibility = View.VISIBLE
            page.animate()
                .alpha(1f).duration =
                page.resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        }

    }

    private val runnableInsideView = Runnable {
        binding.vpAds.currentItem = binding.vpAds.currentItem + 1
    }

    // Add the next item to be the current item in the view pager after some specified time
    private fun setupAdImagesViewPagerCallback() {
        binding.vpAds.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnableInsideView)
                handler.postDelayed(runnableInsideView, Constants.ADS_TRANSITION_ANIMATION_DELAY)
            }
        })
    }

    private fun showError() {
        Toast.makeText(requireContext(),"Check Your Network Connection",Toast.LENGTH_SHORT).show()
        binding.prodectsProgress.visibility = View.GONE
        binding.brandsProgress.visibility = View.GONE
    }

    private fun initBrands(result: List<SmartCollection>) {
        binding.brandsProgress.visibility = View.GONE
        binding.brandRecView.adapter = BrandAdapter(requireContext(), result, this)
        binding.brandRecView.visibility = View.VISIBLE
    }

    private fun initProducts(result : List<Product>){
        binding.prodectsProgress.visibility = View.GONE
        binding.productsRecView.adapter = ProductsAdapter(requireContext(),result,"USD", this)
        binding.productsRecView.visibility = View.VISIBLE
    }

    private fun loadingUi() {
        binding.brandsProgress.visibility = View.VISIBLE
        binding.prodectsProgress.visibility = View.VISIBLE
        binding.brandRecView.visibility = View.INVISIBLE
        binding.productsRecView.visibility = View.INVISIBLE
    }

    override fun onItemClicked(position: Int) {
        Log.i("Exception", "onItemClicked executed at pos $position")
        val promocode = Promocode()
        when (position) {
            0 -> promocode.percentage = "50"
            1 -> promocode.percentage = "30"
            2 -> promocode.percentage = "40"
            3 -> promocode.percentage = "50"
        }
        showPromocodeDialog(position, promocode)
    }

    private fun setupPromocodeDialog(position: Int, promocode: Promocode): Dialog {
        val dialog = Dialog(requireContext())
        setupPromocodeDialogWindow(dialog)
        val tvTitle: TextView = dialog.findViewById(R.id.dialog_title)
        val tvMessage: TextView = dialog.findViewById(R.id.dialog_message)
        tvTitle.append(" \"${Constants.promocodes[position]}\"")
        val fullMessage =
            "${resources.getString(R.string.promocode_dialog_message)} ${promocode.percentage}% code"
        tvMessage.text = fullMessage
        setupPromocodeDialogButtonsActions(dialog)
        dialog.setCancelable(true)

        return dialog
    }

    private fun setupPromocodeDialogButtonsActions(dialog: Dialog) {
        val btnReclaim: Button = dialog.findViewById(R.id.btn_reclaim)
        val tvCancel: TextView = dialog.findViewById(R.id.tv_cancel)
        btnReclaim.setOnClickListener {
            // TODO reclaim the actual promocode and save it to firebase database -optional room database as well-
            Toast.makeText(requireContext(), "Reclaimed", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        tvCancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun setupPromocodeDialogWindow(dialog: Dialog) {
        dialog.window?.setBackgroundDrawableResource(R.drawable.custom_dialog_shape)
        dialog.setContentView(R.layout.custom_dialog_layout)
        val metrics: DisplayMetrics = resources.displayMetrics
        val width: Int = metrics.widthPixels
        dialog.window?.setLayout(width - 80, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showPromocodeDialog(position: Int, promocode: Promocode) {
        setupPromocodeDialog(position, promocode).show()
    }

    override fun navigateToProductsScreen(collectionId: Long, collectionTitle: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToProductsFragment3(collectionId,collectionTitle)
        )
    }

    override fun navigateToDetailsScreen(currentProduct: Product) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToProductDetailsFragment(currentProduct)
        )
    }

}

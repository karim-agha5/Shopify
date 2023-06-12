package com.example.shopify.features.home.view.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shopify.R
import com.example.shopify.core.common.interfaces.RecyclerViewItemClickListener
import com.example.shopify.core.util.ApiState
import com.example.shopify.core.util.Constants
import com.example.shopify.databinding.FragmentHomeBinding
import com.example.shopify.features.home.network.RetrofitClient
import com.example.shopify.features.home.repository.Repository
import com.example.shopify.features.home.view.AdImagesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment(),RecyclerViewItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adImagesAdapter: AdImagesAdapter
    private var handler: Handler = Handler(Looper.myLooper()!!)
    private var imageList = ArrayList<Int>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModelFactory = HomeViewModelFactory(
            Repository.getInstance(RetrofitClient.getInstance())
        )
        val homeViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.brandRecView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val root: View = binding.root
        lifecycleScope.launch {
            homeViewModel.stateFlow.collectLatest { state ->
                when (state) {
                    is ApiState.Success -> {
                        initUI(state)
                    }
                    is ApiState.Failure -> {
                        showError(state)
                    }
                    else -> {
                        loadingUi()
                    }
                }
            }
        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addImagesToList()
        setupAdImagesViewPager()
        setupAdImageTransformation()
        setupAdImagesViewPagerCallback()
    }


    private fun addImagesToList(){
        imageList.add(R.drawable.ad_image_1_sale)
        imageList.add(R.drawable.ad_image_2_coupon)
        imageList.add(R.drawable.ad_image_3_coupon)
        imageList.add(R.drawable.ad_image_4_coupon)
    }

    private fun setupAdImagesViewPager(){
        adImagesAdapter = AdImagesAdapter(imageList,binding.vpAds,this)
        binding.vpAds.adapter = adImagesAdapter
        binding.vpAds.offscreenPageLimit = 4
        binding.vpAds.clipChildren = false
        binding.vpAds.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.vpAds.isUserInputEnabled = false
    }

    // Transition animation between ad pages in the viewpager
    private fun setupAdImageTransformation(){
        binding.vpAds.setPageTransformer { page, _ ->
            page.alpha = 0f
            page.visibility = View.VISIBLE
            page.animate()
                .alpha(1f).duration =
                page.resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        }

    }

    private val runnableInsideView = Runnable{
        binding.vpAds.currentItem = binding.vpAds.currentItem + 1
    }

    // Add the next item to be the current item in the view pager after some specified time
    private fun setupAdImagesViewPagerCallback(){
        binding.vpAds.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnableInsideView)
                handler.postDelayed(runnableInsideView,Constants.ADS_TRANSITION_ANIMATION_DELAY)
            }
        })
    }

    private fun showError(err : ApiState.Failure){
        println(err.msg)
    }

    private fun initUI(result :ApiState.Success){
        binding.brandsProgress.visibility = View.INVISIBLE
        binding.brandRecView.adapter = BrandAdapter(requireContext(),result.smartCollection)
        binding.brandRecView.visibility = View.VISIBLE
    }

    private fun loadingUi() {
        binding.brandsProgress.visibility = View.VISIBLE
        binding.brandRecView.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(position: Int) {
        Log.i("Exception", "onItemClicked executed at pos ${(position * 2) - imageList.size}")
    }
}
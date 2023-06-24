package com.example.shopify.features.wishlist.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.remote.DraftOrderRemoteSourceImpl
import com.example.shopify.databinding.FragmentWishlistBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModel
import com.example.shopify.features.product_details.viewmodel.ProductDetailsViewModelFactory
import com.example.shopify.features.wishlist.viewmodel.WishlistViewModel
import com.example.shopify.features.wishlist.viewmodel.WishlistViewModelFactory


class WishlistFragment : Fragment(), IOnFavoriteClickListener {
    private val TAG = "WishlistFragment"
    private lateinit var binding: FragmentWishlistBinding
    private lateinit var wishlistAdapter: WishlistAdapter
    private lateinit var wishlistViewModel: WishlistViewModel
    private lateinit var factory: WishlistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater)

        wishlistAdapter = WishlistAdapter(requireContext(),this)
        binding.rvFavs.adapter = wishlistAdapter

        factory = WishlistViewModelFactory(
            (activity as MainActivity).customerInfo?.wishListId,
            DraftOrderRepositoryImpl(DraftOrderRemoteSourceImpl(RetrofitHelper.getInstance()))
        )

        wishlistViewModel = ViewModelProvider(this, factory).get(WishlistViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishlistAdapter.submitProductsList((activity as MainActivity).favsList)
        Log.d(TAG, "onViewCreated: ${(activity as MainActivity).favsList}")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.toolbar.findViewById<SearchView>(R.id.searchView).visibility = View.GONE
        (activity as MainActivity).binding.toolbar.navigationIcon = null
    }

    override fun navigateToDetails(product: Product) {
        findNavController().navigate(WishlistFragmentDirections.actionWishlistFragmentToProductDetailsFragment(product))
    }

}
package com.example.shopify.features.home.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopify.core.util.ApiState
import com.example.shopify.databinding.FragmentHomeBinding
import com.example.shopify.features.home.network.RetrofitClient
import com.example.shopify.features.home.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

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
        binding.brandRecView.layoutManager = GridLayoutManager(requireContext(),2,
            GridLayoutManager.HORIZONTAL,false)
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
}
package com.example.shopify.features.onboarding

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.shopify.R
import com.example.shopify.core.util.SharedPreferencesHelper
import com.example.shopify.features.MainActivity
import com.example.shopify.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    private val TAG = "OnboardingFragment"

    lateinit var pager: ViewPager
    lateinit var adapter: ViewPagerAdapter
    lateinit var btnContinue: Button
    private lateinit var binding: FragmentOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater)
        binding.onboardingFragment = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).window.statusBarColor = resources.getColor(R.color.backgroundGray)
        btnContinue = view.findViewById(R.id.btnOnboardingContinue)
        pager = view.findViewById(R.id.pager)
        adapter = ViewPagerAdapter(childFragmentManager,1)
        pager.adapter = adapter

        btnContinue.setOnClickListener {
            if(pager.currentItem == 2) {
                SharedPreferencesHelper.getInstance(requireContext()).saveString("is_onboarding_done","yes")
                navigateToHome()
            }
            pager.currentItem = pager.currentItem + 1
        }

    }

    private fun navigateToHome(){
        findNavController().navigate(R.id.action_onboardingFragment_to_navigation_home)
    }

    fun navigateToHome(view: View){//if skip is pressed
        Log.d(TAG, "navigateToHome: ")
        SharedPreferencesHelper.getInstance(requireContext()).saveString("is_onboarding_done","yes")
        findNavController().navigate(R.id.action_onboardingFragment_to_navigation_home)
    }
}
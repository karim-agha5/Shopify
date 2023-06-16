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
        btnContinue = view.findViewById(R.id.btnOnboardingContinue)
        pager = view.findViewById(R.id.pager)
        adapter = ViewPagerAdapter(childFragmentManager,1)
        pager.adapter = adapter

        btnContinue.setOnClickListener {
            if(pager.currentItem == 2) {
                navigateToAuthentication()
            }
            pager.currentItem = pager.currentItem + 1
        }

    }

    private fun navigateToAuthentication(){
        findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
    }

    fun navigateToHome(view: View){
        Log.d(TAG, "navigateToHome: ")
        findNavController().navigate(R.id.action_onboardingFragment_to_navigation_home)
    }
}
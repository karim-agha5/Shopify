package com.example.shopify.features.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.shopify.R
import com.example.shopify.features.MainActivity

class OnboardingFragment : Fragment() {


    lateinit var pager: ViewPager
    lateinit var adapter: ViewPagerAdapter
    lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
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
                navigateToAuthentication()
            }
            pager.currentItem = pager.currentItem + 1
        }
    }

    private fun navigateToAuthentication(){
        findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
    }
}
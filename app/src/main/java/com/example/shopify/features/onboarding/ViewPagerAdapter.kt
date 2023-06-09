package com.example.shopify.features.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.shopify.features.onboarding.view.FirstFragment
import com.example.shopify.features.onboarding.view.SecondFragment
import com.example.shopify.features.onboarding.view.ThirdFragment

class ViewPagerAdapter(fm: FragmentManager,behavior: Int) : FragmentPagerAdapter(fm,behavior) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> ThirdFragment()
        }
    }
}
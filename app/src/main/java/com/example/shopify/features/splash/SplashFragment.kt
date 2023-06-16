package com.example.shopify.features.splash

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.setPadding
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.databinding.FragmentSplashBinding
import com.example.shopify.features.MainActivity

class SplashFragment : Fragment() {

    private val SPLASH_SCREEN_DELAY_TIME = 3000L
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler()
            .postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment2)
            },SPLASH_SCREEN_DELAY_TIME)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.toolbar.visibility = View.GONE
    }
}
package com.example.shopify.features.splash

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.setPadding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.local.firebase.FirebaseDataManager
import com.example.shopify.core.common.features.usersettings.UserSettingsDataStore
import com.example.shopify.core.util.SharedPreferencesHelper
import com.example.shopify.databinding.FragmentSplashBinding
import com.example.shopify.features.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private val TAG = "SplashFragment"
    private val SPLASH_SCREEN_DELAY_TIME = 3000L
    private lateinit var binding: FragmentSplashBinding
    private val userSettingsDataStore by lazy {
        UserSettingsDataStore.getInstance(requireActivity().application)
    }
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
        (activity as MainActivity).window.statusBarColor = resources.getColor(R.color.splashScreenBg)
       /* if(SharedPreferencesHelper.getInstance(requireContext()).getString("is_onboarding_done","no") == "no"){
            Handler()
                .postDelayed({
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment2)
                },SPLASH_SCREEN_DELAY_TIME)
        }else{
            Handler()
                .postDelayed({
                    findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
                },SPLASH_SCREEN_DELAY_TIME)
        }*/

        lifecycleScope.launch {
            // TODO refactor later to take this intance from the application class
            if(userSettingsDataStore.readIsFirstTimeUser() == null){
                userSettingsDataStore.writeIsFirstTimeUser(true)
                Handler()
                    .postDelayed({
                        findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment2)
                    },SPLASH_SCREEN_DELAY_TIME)
            }
            else{
                Handler()
                    .postDelayed({
                        findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
                    },SPLASH_SCREEN_DELAY_TIME)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.linearLayout.setPadding(0)
        (activity as MainActivity).binding.toolbar.visibility = View.GONE

    }
}
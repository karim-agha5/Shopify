package com.example.shopify.features

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var view: ConstraintLayout
    var customerInfo: CustomerResponseInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()


        //  window.statusBarColor = resources.getColor(android.R.color.transparent)

       // window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // layout the app behind the system bars
        //WindowCompat.setDecorFitsSystemWindows(window, false)

       // adjustContentInsets()
/*
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_splashFragment_to_onboardingFragment2)
*/
        setupNav()
    }

    private fun initUI(){
        view = findViewById(R.id.view)
        setSupportActionBar(binding.toolbar)
    }

            private fun setupNav() {
            val navController = findNavController(R.id.fragment_container_view)
            findViewById<BottomNavigationView>(R.id.nav_view)
                .setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.onboardingFragment -> hideBottomNav()
                    R.id.registrationFragment -> hideBottomNav()
                    R.id.splashFragment -> hideBottomNav()
                    R.id.loginFragment -> hideBottomNav()
                    else -> showBottomNav()
                }
            }
        }

        private fun showBottomNav() {
            binding.navView.visibility = View.VISIBLE
            binding.navView.animate()
                .translationY(0f)
                .duration = 4
        }

        private fun hideBottomNav() {
            binding.navView.visibility = View.GONE
        }

    private fun adjustContentInsets(){
        ViewCompat.setOnApplyWindowInsetsListener(view) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updateLayoutParams<MarginLayoutParams> {
                leftMargin = insets.left
                bottomMargin = insets.bottom
                rightMargin = insets.right
            }

            WindowInsetsCompat.CONSUMED
        }
    }
}
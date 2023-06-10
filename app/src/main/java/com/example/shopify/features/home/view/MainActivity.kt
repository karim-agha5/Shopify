package com.example.shopify.features.home.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopify.R
import com.example.shopify.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var view: ConstraintLayout

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

    }

            private fun setupNav() {
            val navController = findNavController(R.id.fragment_container_view)
            findViewById<BottomNavigationView>(R.id.nav_view)
                .setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.navigation_home -> showBottomNav()
                    R.id.navigation_categories -> showBottomNav()
                    R.id.navigation_me -> showBottomNav()
                    else -> {
                        hideBottomNav()
                    }
                }
            }
        }

        private fun showBottomNav() {
            binding.navView.visibility = View.VISIBLE
            binding.navView.alpha = 0.0f
            binding.navView.animate()
                .translationY(0f)
                .alpha(1.0f)
                .duration = 1000
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
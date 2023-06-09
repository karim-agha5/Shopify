package com.example.shopify.features.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shopify.databinding.ActivityMainBinding
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.shopify.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var view: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        window.statusBarColor = resources.getColor(android.R.color.transparent)

        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // layout the app behind the system bard
        WindowCompat.setDecorFitsSystemWindows(window, false)

        adjustContentInsets()
/*
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_splashFragment_to_onboardingFragment2)
*/


    }

    private fun initUI(){
        view = findViewById(R.id.view)
    }

    private fun adjustContentInsets(){
        ViewCompat.setOnApplyWindowInsetsListener(view) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updateLayoutParams<MarginLayoutParams> {
                leftMargin = insets.left
                //   bottomMargin = insets.bottom
                rightMargin = insets.right
            }

            WindowInsetsCompat.CONSUMED
        }
    }
}
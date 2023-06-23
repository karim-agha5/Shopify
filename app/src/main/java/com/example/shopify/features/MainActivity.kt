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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.local.firebase.FirebaseDataManager
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.util.SharedPreferencesHelper
import com.example.shopify.databinding.ActivityMainBinding
import com.example.shopify.features.checkout.paymentgateway.stripe.service.StripeRetrofitHelper
import com.example.shopify.features.checkout.paymentgateway.stripe.service.StripeService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    private lateinit var view: ConstraintLayout
    private var auth: FirebaseAuth
    var customerInfo: CustomerResponseInfo? = null

    init {
        auth = Firebase.auth
        if(auth.currentUser != null){
            Log.d(TAG, "inti: logged in and ${auth.currentUser?.email}")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

        //saving to shared preferences
        if (SharedPreferencesHelper.getInstance(this)
                .getString("is_onboarding_done", "non") == "non"
        ) {
            //it's first time opening the app
            SharedPreferencesHelper.getInstance(this).saveString("is_onboarding_done", "no")
        }




/*


        val retrofit = StripeRetrofitHelper.getInstance()
        val service = retrofit.create(StripeService::class.java)
        lifecycleScope.launch(Dispatchers.IO){
           try{
               //val response = service.createStripeCustomer()
               //val emphKey = service.getEphemeralKey("cus_O88cdgHCGGI5YI")
                val response = service.submitPaymentIntent("cus_O88cdgHCGGI5YI",1000,"usd")
               Log.i("Exception", "id = ${response?.id}\n" +
                       "amount = ${response?.amount}")
           }
           catch (ex: HttpException){
               Log.i("Exception", "${ex.message}\n")
           }
        }

*/



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

    override fun onResume() {
        super.onResume()
        if(auth.currentUser != null){
            FirebaseDataManager.getCustomerByEmail(auth.currentUser?.email!!){
                Log.d(TAG, "init: $it")
                customerInfo = it
            }
        }
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.fragment_container_view)

        // Check if the current destination is the "Personal" tab
        if (navController.currentDestination?.id == R.id.navigation_me ||
            navController.currentDestination?.id == R.id.navigation_categories ||
                navController.currentDestination?.id == R.id.shoppingCartFragment) {
            // Navigate to the "Home" tab
//            navController.navigate(R.id.navigation_home)
            navController.popBackStack(R.id.navigation_home, false)
        } else {
            // Perform the default back button behavior (navigate up)
            super.onBackPressed()
        }
    }

    private fun initUI() {
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

    private fun adjustContentInsets() {
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
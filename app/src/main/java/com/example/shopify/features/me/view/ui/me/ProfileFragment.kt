package com.example.shopify.features.me.view.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.databinding.FragmentProfileBinding
import com.example.shopify.features.MainActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth : FirebaseAuth
    private var userEmail : String? = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding = FragmentProfileBinding.inflate(inflater, container, false)

         auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            binding.loggedOutLayout.visibility = View.GONE
            binding.loggedInLayout.visibility = View.VISIBLE
            userEmail = (activity as MainActivity).customerInfo?.firstName + " " + ((activity as MainActivity).customerInfo?.lastName
                ?: "")

            initLoggedInNavigation()

        } else {
            binding.loggedInLayout.visibility = View.GONE
            binding.loggedOutLayout.visibility = View.VISIBLE
            initLoggedOutNavigation()
            userEmail = "Nice to meet you"
        }

        binding.welcomingText.text = getString(R.string.profileWelcome_message, userEmail)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).binding.toolbar.findViewById<SearchView>(R.id.searchView).visibility = View.GONE
        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (activity as MainActivity).binding.toolbar.navigationIcon = null
    }

    private fun initLoggedInNavigation(){
        binding.settingsConstraint.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationMeToSettingsFragment())
        }
        binding.myCartConstraint.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationMeToShoppingCartFragment())
        }

        binding.myOrdersConstraint.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationMeToOrdersFragment())
        }
        binding.signOutConstraint.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionNavigationMeToNavigationHome())
        }
    }

    private fun initLoggedOutNavigation() {
        binding.logInBtn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationMeToLoginFragment())
        }
        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationMeToRegistrationFragment())
        }
    }

}
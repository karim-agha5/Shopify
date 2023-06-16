package com.example.shopify.features.authentication.login.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.databinding.FragmentLoginBinding
import com.example.shopify.features.authentication.login.viewmodel.LoginViewModel
import com.example.shopify.features.authentication.login.viewmodel.LoginViewModelFactory
import com.example.shopify.features.authentication.registration.data.RegistrationRepository
import com.example.shopify.features.authentication.registration.data.remote.RemoteRegistrationRemoteSource
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModel
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModelFactory
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var factory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginFragment = this

        factory =
            LoginViewModelFactory(requireActivity())
        loginViewModel =
            ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        return binding.root
    }

    fun validateTextField(view: View) {
        var isValid: Boolean = true
        if (binding.tfEmail.editText?.text.toString().isEmpty()) {
            binding.tfEmail.requestFocus()
            binding.tfEmail.error = "Email is required"
            isValid = false
        } else {
            Log.d("TAG", "validateTextField: ")
            binding.tfEmail.error = null
            binding.tfEmail.clearFocus()
            isValid = true
        }
        if (binding.tfPassword.editText?.text.toString().isEmpty()) {
            binding.tfPassword.requestFocus()
            binding.tfPassword.error = "Password is required"
            isValid = false
        } else {
            binding.tfPassword.error = null
            binding.tfPassword.clearFocus()
            isValid = true
        }
        //navigate
        if (isValid) {
            binding.btnLogin.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            Log.d(TAG, "validateTextField: true")
            loginViewModel.loginCustomerFirebase(
                binding.tfEmail.editText!!.text.toString(),
                binding.tfPassword.editText!!.text.toString()
            ){isSuccessful->
                binding.progressBar.visibility = View.GONE
                binding.btnLogin.visibility = View.VISIBLE

                if(isSuccessful){
                    Toast.makeText(requireContext(),"Login Successfully",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),"Login Failed",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun navigateToRegister(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }
}
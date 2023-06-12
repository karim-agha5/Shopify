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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginFragment = this
        return binding.root
    }

    fun validateTextField(view: View) {
        var isValid: Boolean = false
        if (binding.tfEmail.editText?.text.toString().isEmpty()) {
            binding.tfEmail.requestFocus()
            binding.tfEmail.error = "Email is required"
            isValid = true
        } else {
            Log.d("TAG", "validateTextField: ")
            binding.tfEmail.error = null
            binding.tfEmail.clearFocus()
            isValid = false
        }
        if (binding.tfPassword.editText?.text.toString().isEmpty()) {
            binding.tfPassword.requestFocus()
            binding.tfPassword.error = "Password is required"
            isValid = true
        } else {
            binding.tfPassword.error = null
            binding.tfPassword.clearFocus()
            isValid = false
        }
        //navigate
        if(isValid){
            Log.d(TAG, "validateTextField: ")
        }
    }

    fun navigateToRegister(view: View){
        view.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }
}
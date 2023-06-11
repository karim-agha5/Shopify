package com.example.shopify.features.authentication.registration.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.databinding.FragmentRegistrationBinding
import com.google.android.material.textfield.TextInputLayout

class RegistrationFragment : Fragment() {
    private val TAG = "RegistrationFragment"
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }
    fun validateTextField(view: View) {
        var isValid = true

        /*if (binding.tfName.editText?.text.toString().isEmpty()) {
            binding.tfName.requestFocus()
            binding.tfName.error = "Name is required"
            isValid = false
        } else if (!binding.tfName.editText?.text.toString().matches("[a-zA-Z ]+".toRegex())) {
            binding.tfName.requestFocus()
            binding.tfName.error = "Please enter a valid name"
            isValid = false
        } else {
            binding.tfName.error = null
            binding.tfName.clearFocus()
        }

        if (binding.tfEmail.editText?.text.toString().isEmpty()) {
            binding.tfEmail.requestFocus()
            binding.tfEmail.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.tfEmail.editText?.text.toString()).matches()) {
            binding.tfEmail.requestFocus()
            binding.tfEmail.error = "Please enter a valid email"
            isValid = false
        } else {
            binding.tfEmail.error = null
            binding.tfEmail.clearFocus()
        }

        if (binding.tfPassword.editText?.text.toString().isEmpty()) {
            binding.tfPassword.requestFocus()
            binding.tfPassword.error = "Password is required"
            isValid = false
        } else if (!binding.tfPassword.editText?.text.toString().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}\$".toRegex())) {
            binding.tfPassword.requestFocus()
            binding.tfPassword.error = """
            Password must contain:
            - At least 8 characters long
            - Contains at least one letter (uppercase or lowercase)
            - Contains at least one digit
            - Contains at least one special character (e.g., @$!%*#?&)
        """.trimIndent()
            isValid = false
        } else {
            binding.tfPassword.error = null
            binding.tfPassword.clearFocus()

            if (!binding.tfConfirmPassword.editText?.text.toString().equals(binding.tfPassword.editText?.text.toString())) {
                binding.tfConfirmPassword.requestFocus()
                binding.tfConfirmPassword.error = "It doesn't match the password above"
                isValid = false
            } else {
                binding.tfConfirmPassword.error = null
                binding.tfConfirmPassword.clearFocus()
            }
        }

        if (binding.tfConfirmPassword.editText?.text.toString().isEmpty()) {
            binding.tfConfirmPassword.requestFocus()
            binding.tfConfirmPassword.error = "Confirmation of password is required"
            isValid = false
        } else {
            binding.tfConfirmPassword.error = null
            binding.tfConfirmPassword.clearFocus()
        }*/

        if (isValid) {
            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment2)
        }
    }

    fun navigateToLogin(view: View){
        //navigation
        Log.d(TAG, "navigateToLogin: ")
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}
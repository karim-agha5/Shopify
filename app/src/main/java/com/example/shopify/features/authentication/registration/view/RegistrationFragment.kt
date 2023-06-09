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
import com.google.android.material.textfield.TextInputLayout

class RegistrationFragment : Fragment() {
    private val TAG = "RegistrationFragment"
    lateinit var btn: Button
    lateinit var tfEmail: TextInputLayout
    lateinit var tfName: TextInputLayout
    lateinit var tfPassword: TextInputLayout
    lateinit var tfConfirmPassword: TextInputLayout
    lateinit var tvLogin: TextView
    lateinit var tvRightArrow: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn = view.findViewById(R.id.btn_signup)
        tfEmail = view.findViewById(R.id.tf_email)
        tfName = view.findViewById(R.id.tf_name)
        tfPassword = view.findViewById(R.id.tf_password)
        tfConfirmPassword = view.findViewById(R.id.tf_confirm_password)
        tvLogin = view.findViewById(R.id.tv_have_account)
        tvRightArrow = view.findViewById(R.id.tv_rightArrow)

        btn.setOnClickListener {
            validateTextField()
        }

        tvLogin.setOnClickListener {
            navigateToLogin()
        }
        tvRightArrow.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun validateTextField() {
        var isValid = true

        if (tfName.editText?.text.toString().isEmpty()) {
            tfName.requestFocus()
            tfName.error = "Name is required"
            isValid = false
        } else if (!tfName.editText?.text.toString().matches("[a-zA-Z ]+".toRegex())) {
            tfName.requestFocus()
            tfName.error = "Please enter a valid name"
            isValid = false
        } else {
            tfName.error = null
            tfName.clearFocus()
        }

        if (tfEmail.editText?.text.toString().isEmpty()) {
            tfEmail.requestFocus()
            tfEmail.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(tfEmail.editText?.text.toString()).matches()) {
            tfEmail.requestFocus()
            tfEmail.error = "Please enter a valid email"
            isValid = false
        } else {
            tfEmail.error = null
            tfEmail.clearFocus()
        }

        if (tfPassword.editText?.text.toString().isEmpty()) {
            tfPassword.requestFocus()
            tfPassword.error = "Password is required"
            isValid = false
        } else if (!tfPassword.editText?.text.toString().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}\$".toRegex())) {
            tfPassword.requestFocus()
            tfPassword.error = """
            Password must contain:
            - At least 8 characters long
            - Contains at least one letter (uppercase or lowercase)
            - Contains at least one digit
            - Contains at least one special character (e.g., @$!%*#?&)
        """.trimIndent()
            isValid = false
        } else {
            tfPassword.error = null
            tfPassword.clearFocus()

            if (!tfConfirmPassword.editText?.text.toString().equals(tfPassword.editText?.text.toString())) {
                tfConfirmPassword.requestFocus()
                tfConfirmPassword.error = "It doesn't match the password above"
                isValid = false
            } else {
                tfConfirmPassword.error = null
                tfConfirmPassword.clearFocus()
            }
        }

        if (tfConfirmPassword.editText?.text.toString().isEmpty()) {
            tfConfirmPassword.requestFocus()
            tfConfirmPassword.error = "Confirmation of password is required"
            isValid = false
        } else {
            tfConfirmPassword.error = null
            tfConfirmPassword.clearFocus()
        }

        if (isValid) {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }


    private fun navigateToLogin(){
        //navigation
        Log.d(TAG, "navigateToLogin: ")
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}
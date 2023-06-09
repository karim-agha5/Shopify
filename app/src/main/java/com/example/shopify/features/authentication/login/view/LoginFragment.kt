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
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"
    lateinit var tfEmail: TextInputLayout
    lateinit var tfPassword: TextInputLayout
    private lateinit var tvRegister: TextView
    private lateinit var tvLeftArrow: TextView
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tfEmail = view.findViewById(R.id.tf_email)
        tfPassword = view.findViewById(R.id.tf_password)
        btn = view.findViewById(R.id.btn_login)
        tvRegister = view.findViewById(R.id.tv_register)
        tvLeftArrow = view.findViewById(R.id.tv_LeftArrow)
        btn.setOnClickListener{
            Log.d(TAG, "onViewCreated: ")
            validateTextField()
        }
        tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        tvLeftArrow.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun validateTextField() {
        var isValid: Boolean
        if (tfEmail.editText?.text.toString().isEmpty()) {
            tfEmail.requestFocus()
            tfEmail.error = "Email is required"
            isValid = true
        } else {
            Log.d("TAG", "validateTextField: ")
            tfEmail.error = null
            tfEmail.clearFocus()
            isValid = false
        }
        if (tfPassword.editText?.text.toString().isEmpty()) {
            tfPassword.requestFocus()
            tfPassword.error = "Password is required"
            isValid = true
        } else {
            tfPassword.error = null
            tfPassword.clearFocus()
            isValid = false
        }
        //navigate
        if(isValid){
            Log.d(TAG, "validateTextField: ")
        }
    }
}
package com.example.shopify.features.authentication.registeration.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.shopify.R
import com.google.android.material.textfield.TextInputLayout

class RegisterationActivity : AppCompatActivity() {
    private val TAG = "RegisterationActivity"
    lateinit var btn: Button
    lateinit var tfEmail: TextInputLayout
    lateinit var tfName: TextInputLayout
    lateinit var tfPassword: TextInputLayout
    lateinit var tfConfirmPassword: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration)

        btn = findViewById(R.id.btn_signup)
        tfEmail = findViewById(R.id.tf_email)
        tfName = findViewById(R.id.tf_name)
        tfPassword = findViewById(R.id.tf_password)
        tfConfirmPassword = findViewById(R.id.tf_confirm_password)
    }

    fun validateTextField(view: View) {

        if (tfName.editText?.text.toString().isEmpty()) {
            tfName.requestFocus()
            tfName.error = "Name is required"
        } else if (!tfName.editText?.text.toString().matches("[a-zA-Z]+".toRegex())) {
            tfName.requestFocus()
            tfName.error = "Please enter a valid name"
        } else {
            tfName.error = null
            tfName.clearFocus()
        }
        if (tfEmail.editText?.text.toString().isEmpty()) {
            tfEmail.requestFocus()
            tfEmail.error = "Email is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(tfEmail.editText?.text.toString()).matches()) {
            tfEmail.requestFocus()
            tfEmail.error = "Please enter a valid email"
        } else {
            Log.d("TAG", "validateTextField: ")
            tfEmail.error = null
            tfEmail.clearFocus()
        }
        if (tfPassword.editText?.text.toString().isEmpty()) {
            tfPassword.requestFocus()
            tfPassword.error = "Password is required"
        } else if (!tfPassword.editText?.text.toString().matches(
                "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}\$"
                    .toRegex()
            )
        ) {
            tfPassword.requestFocus()
            tfPassword.error = """
    Password must contain:
    - At least 8 characters long
    - Contains at least one letter (uppercase or lowercase)
    - Contains at least one digit
    - Contains at least one special character (e.g., @$!%*#?&)
""".trimIndent()
        } else {
            tfPassword.error = null
            tfPassword.clearFocus()
            if (tfConfirmPassword.editText?.text.toString()
                    .equals(tfPassword.editText?.text.toString())
            ) {
                tfConfirmPassword.error = null
                tfConfirmPassword.clearFocus()
                //navigate
            } else {
                tfConfirmPassword.requestFocus()
                tfConfirmPassword.error = "It doesn't match the password above"
            }
        }

        if (tfConfirmPassword.editText?.text.toString().isEmpty()) {
            tfConfirmPassword.requestFocus()
            tfConfirmPassword.error = "Confirmation of password is required"
        } else {
            tfConfirmPassword.error = null
            tfConfirmPassword.clearFocus()
        }
    }

    fun navigateToLogin(view: View){
        //navigation
        Log.d(TAG, "navigateToLogin: ")
    }
}
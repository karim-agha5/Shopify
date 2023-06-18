package com.example.shopify.features.authentication.registration.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerRegistrationInfo
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.remote.DraftOrderRemoteSourceImpl
import com.example.shopify.core.util.ApiState2
import com.example.shopify.databinding.FragmentRegistrationBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.authentication.registration.data.RegistrationRepository
import com.example.shopify.features.authentication.registration.data.remote.CreationDraftOrderRemoteSource
import com.example.shopify.features.authentication.registration.data.remote.RegistrationRemoteSource
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModel
import com.example.shopify.features.authentication.registration.viewmodel.RegistrationViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment() {
    private val TAG = "RegistrationFragment"
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var factory: RegistrationViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        binding.registerFragment = this

        factory =
            RegistrationViewModelFactory(
                RegistrationRepository(
                    RegistrationRemoteSource(),
                    CreationDraftOrderRemoteSource()
                ),
                DraftOrderRepositoryImpl(DraftOrderRemoteSourceImpl(RetrofitHelper.getInstance())),
                requireActivity()
            )
        registrationViewModel =
            ViewModelProvider(this, factory).get(RegistrationViewModel::class.java)


        return binding.root
    }

    fun validateTextField(view: View) {
        var isValid = true

        if (binding.tfName.editText?.text.toString().isEmpty()) {
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
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.tfEmail.editText?.text.toString())
                .matches()
        ) {
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
        } else if (!binding.tfPassword.editText?.text.toString()
                .matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}\$".toRegex())
        ) {
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
        }

        if (binding.tfConfirmPassword.editText?.text.toString().isEmpty()) {
            binding.tfConfirmPassword.requestFocus()
            binding.tfConfirmPassword.error = "Confirmation of password is required"
            isValid = false
        } else if (!binding.tfConfirmPassword.editText?.text.toString()
                .equals(binding.tfPassword.editText?.text.toString())
        ) {
            binding.tfConfirmPassword.requestFocus()
            binding.tfConfirmPassword.error = "It doesn't match the password above"
            isValid = false
        } else {
            binding.tfConfirmPassword.error = null
            binding.tfConfirmPassword.clearFocus()
        }

        if (isValid) {
            Log.d(TAG, "validateTextField: pressed\n")
            binding.btnSignup.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            lifecycleScope.launchWhenResumed {
                registrationViewModel.customerStateFlow.collect {
                    when (it) {
                        is ApiState2.Success -> {
                            Log.d(TAG, "validateTextField: ${it.data}")
                            withContext(Dispatchers.Main) {
                                binding.btnSignup.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(activity, "Data received", Toast.LENGTH_SHORT).show()
                                // TODO change later to navigate back to settings

                                (activity as MainActivity).customerInfo = it.data.customer
                                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment2())
                            }
                        }

                        is ApiState2.Failure -> {
                            Log.w(TAG, "validateTextField: ${it.exception.message}")
                            withContext(Dispatchers.Main) {
                                binding.btnSignup.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE

                                //show error msg
                                binding.tfEmail.requestFocus()
                                binding.tfEmail.error = "Email Already in use"
//                                Toast.makeText(requireContext(),"Error happend",Toast.LENGTH_SHORT).show()
                            }
                        }

                        else -> {
                            //TODO loading
                        }
                    }
                }
            }
            registrationViewModel.registerCustomer(
                CustomerRegistration(
                    CustomerRegistrationInfo(
                        firstName = binding.tfName.editText?.text.toString(),
                        email = binding.tfEmail.editText?.text.toString(),
                        password = binding.tfPassword.editText?.text.toString(),
                        passwordConfirmation = binding.tfConfirmPassword.editText?.text.toString()
                    )
                ),
                binding.tfPassword.editText?.text.toString()
            )
        }
    }

    fun navigateToLogin(view: View) {
        //navigation
        Log.d(TAG, "navigateToLogin: ")
        view.findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}
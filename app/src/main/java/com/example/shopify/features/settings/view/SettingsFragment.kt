package com.example.shopify.features.settings.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.shopify.R
import com.example.shopify.databinding.FragmentSettingsBinding
import com.example.shopify.features.MainActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = arrayOf("Item 1","Item 2","Item 3")
        (binding.actvCurrency as MaterialAutoCompleteTextView).setSimpleItems(items)
        setUiListeners()
    }

    private fun setUiListeners(){
        binding.tfStreetName.doOnTextChanged { _, _, _, _ ->
            if(binding.tfStreetName.text?.isEmpty() == true){
                binding.tfStreetName.error = "Required"
            }
        }

        binding.tfCity.doOnTextChanged { _, _, _, _ ->
            if(binding.tfCity.text?.isEmpty() == true){
                binding.tfCity.error = "Required"
            }
        }

        binding.tfCountry.doOnTextChanged { _, _, _, _ ->
            if(binding.tfCountry.text?.isEmpty() == true){
                binding.tfCountry.error = "Required"
            }
        }

        binding.btnSave.setOnClickListener {
            if (areTextFieldsFilled()){
                Toast.makeText(requireContext(), "Can Save", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Cannot Save", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun areTextFieldsFilled() : Boolean{
        var canSave = true
        if(binding.tfStreetName.text?.isEmpty() == true){
            canSave = false
        }
        if(binding.tfCity.text?.isEmpty() == true){
            canSave = false
        }
        if(binding.tfCountry.text?.isEmpty() == true){
            canSave = false
        }
        if(binding.actvCurrency.text.isEmpty()){
            canSave = false
        }
        return canSave
    }
}
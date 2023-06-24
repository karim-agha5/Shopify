package com.example.shopify.features.settings.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopify.R
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.common.features.currency.data.RemoteCurrenciesService
import com.example.shopify.core.common.features.currency.data.remote.RemoteCurrenciesSourceImpl
import com.example.shopify.core.common.features.currency.data.repository.CurrencyRepositoryImpl
import com.example.shopify.core.common.mappers.CurrencyMapper
import com.example.shopify.databinding.FragmentSettingsBinding
import com.example.shopify.features.MainActivity
import com.example.shopify.features.settings.model.CurrencyUiState
import com.example.shopify.features.settings.viewmodel.SettingsViewModel
import com.example.shopify.features.settings.viewmodel.SettingsViewModelFactory
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val settingsViewModel by lazy {
        val retrofit = RetrofitHelper.getInstance()
        val remoteCurrenciesService = retrofit.create(RemoteCurrenciesService::class.java)
        val remoteCurrenciesSource = RemoteCurrenciesSourceImpl(remoteCurrenciesService)
        val currencyRepositoryImpl = CurrencyRepositoryImpl(remoteCurrenciesSource)
        val settingsViewModelFactory = SettingsViewModelFactory(currencyRepositoryImpl,
            CurrencyMapper())
        ViewModelProvider(this,settingsViewModelFactory).get(SettingsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUiListeners()

        lifecycleScope.launch(Dispatchers.Main){
            settingsViewModel.getRemoteCurrencies()
            val currenciesAsStrings = fromCurrenciesUiStateToStrings(settingsViewModel.currenciesUiState.value)
            (binding.actvCurrency as MaterialAutoCompleteTextView).setSimpleItems(currenciesAsStrings)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(binding.actvCurrency as MaterialAutoCompleteTextView).setSimpleItems(currencies)
        (activity as MainActivity).binding.toolbar.setNavigationIcon(R.drawable.baseline_back_arrow_24)
        (activity as MainActivity).binding.toolbar.findViewById<SearchView>(R.id.searchView).visibility = View.GONE
        (activity as MainActivity).binding.toolbar.visibility = View.VISIBLE

        (activity as MainActivity).binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            loadUserSettingsFromDataStore()
        }
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

        binding.tfPhoneNumber.doOnTextChanged { _, _, _, _ ->
            if(binding.tfPhoneNumber.text?.isEmpty() == true){
                binding.tfPhoneNumber.error = "Required"
            }
        }

        binding.btnSave.setOnClickListener {
            if (areTextFieldsFilled()){
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                viewLifecycleOwner.lifecycleScope.launch {
                    writeUserSettingsInDataStore()
                }
                findNavController().navigateUp()
            }
            else{
                Toast.makeText(requireContext(), "Unable to save", Toast.LENGTH_SHORT).show()
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
        if(binding.tfPhoneNumber.text?.isEmpty() == true){
            canSave = false
        }
        if(binding.actvCurrency.text.isEmpty()){
            canSave = false
        }
        return canSave
    }
    private fun navigateToMap(){

    }

    private fun fromCurrenciesUiStateToStrings(currenciesUiStateList: List<CurrencyUiState>) : Array<String> {
        val currenciesAsStrings = mutableListOf<String>()
        for(i in currenciesUiStateList.indices){
            currenciesAsStrings.add(currenciesUiStateList[i].currency ?: "N/A")
        }
        return currenciesAsStrings.toTypedArray()
    }

    private suspend fun writeUserSettingsInDataStore(){
        val userSettingsDataStore = (activity as MainActivity).userSettingsDataStore
        userSettingsDataStore.writeUserBuildingNumber(binding.tfBuildingNumber.text.toString())
        userSettingsDataStore.writeUserStreetName(binding.tfStreetName.text.toString())
        userSettingsDataStore.writeUserCity(binding.tfCity.text.toString())
        userSettingsDataStore.writeUserCountry(binding.tfCountry.text.toString())
        userSettingsDataStore.writeUserPhoneNumber(binding.tfPhoneNumber.text.toString())
        userSettingsDataStore.writeUserCurrency(binding.actvCurrency.text.toString())
    }

    private suspend fun loadUserSettingsFromDataStore(){
        val userSettingsDataStore = (activity as MainActivity).userSettingsDataStore
        binding.tfBuildingNumber.setText(userSettingsDataStore.readUserBuildingNumber())
        binding.tfStreetName.setText(userSettingsDataStore.readUserStreetName())
        binding.tfCity.setText(userSettingsDataStore.readUserCity())
        binding.tfCountry.setText(userSettingsDataStore.readUserCountry())
        binding.tfPhoneNumber.setText(userSettingsDataStore.readUserPhoneNumber())
        binding.actvCurrency.setText(userSettingsDataStore.readUserCurrency())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_fragment_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_location -> navigateToMap()
        }
        return super.onOptionsItemSelected(item)
    }
}
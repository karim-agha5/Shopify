package com.example.shopify.features.category.view.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val categoryViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

}
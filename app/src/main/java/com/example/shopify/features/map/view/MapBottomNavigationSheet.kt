package com.example.shopify.features.map.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopify.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MapBottomNavigationSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_modal_bottom_navigation_sheet_content,container,false)
    }
}
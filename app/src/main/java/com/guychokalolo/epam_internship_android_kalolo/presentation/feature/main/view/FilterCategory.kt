package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guychokalolo.epam_internship_android_kalolo.databinding.CategoryFilterBinding

class FilterCategory : Fragment() {
    lateinit var binding: CategoryFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryFilterBinding.inflate(layoutInflater)
        return binding.root
    }
}
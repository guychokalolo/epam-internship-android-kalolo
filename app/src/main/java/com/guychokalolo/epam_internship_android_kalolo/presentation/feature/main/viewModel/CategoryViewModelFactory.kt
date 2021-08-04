package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetCategoryUseCase
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetMealUseCase

class CategoryViewModelFactory(
    private val categoryUseCase: GetCategoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(categoryUseCase) as T
    }
}
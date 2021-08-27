package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guychokalolo.epam_internship_android_kalolo.domain.useCase.GetMealDetailUseCase

class MealDetailViewModelFactory(private val getMealDetailUseCase: GetMealDetailUseCase) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealDetailViewModel(getMealDetailUseCase) as T
    }
}
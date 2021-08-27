package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guychokalolo.epam_internship_android_kalolo.domain.useCase.GetMealUseCase

class MealViewModelFactory(private val mealItemsUseCase: GetMealUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(mealItemsUseCase) as T
    }
}
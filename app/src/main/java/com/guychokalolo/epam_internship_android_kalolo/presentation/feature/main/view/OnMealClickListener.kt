package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkMealItems
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealEntityUIModel


interface OnMealClickListener {
    fun onFoodItemClicked(mealItems: MealEntityUIModel)
}
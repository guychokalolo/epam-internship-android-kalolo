package com.guychokalolo.epam_internship_android_kalolo

import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems


interface OnMealClickListener {
    fun onFoodItemClicked(mealItems: MealItems)
}
package com.guychokalolo.epam_internship_android_kalolo.`interface`

import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Category
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.CategoryItems
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems

interface OnItemCategory {
    fun onClickCategory(categoryNameItem : CategoryItems)
}
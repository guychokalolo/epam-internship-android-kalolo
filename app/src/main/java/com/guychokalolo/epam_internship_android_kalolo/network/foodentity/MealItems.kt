package com.guychokalolo.epam_internship_android_kalolo.network.foodentity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealItems(
        @SerializedName("strMeal")
        val strMeal: String,

        @SerializedName("strMealThumb")
        val strMealThumb: String,

        @SerializedName("idMeal")
        val idMeal: Int
)
package com.guychokalolo.epam_internship_android_kalolo.network.foodentity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meal(
        @SerializedName("meals")
        val mealItems : List<MealItems>)




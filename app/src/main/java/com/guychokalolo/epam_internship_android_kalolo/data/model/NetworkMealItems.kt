package com.guychokalolo.epam_internship_android_kalolo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkMealItems(
        @SerializedName("strMeal")
        val strMeal: String,
        @SerializedName("strMealThumb")
        val strMealThumb: String,
        @SerializedName("idMeal")
        val idMeal: Int
)
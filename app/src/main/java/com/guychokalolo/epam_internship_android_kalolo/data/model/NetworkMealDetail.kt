package com.guychokalolo.epam_internship_android_kalolo.data.model

import com.google.gson.annotations.SerializedName

data class NetworkMealDetail(
        @SerializedName("meals")
        val mealDetailList: List<NetworkMealDetailList>
)


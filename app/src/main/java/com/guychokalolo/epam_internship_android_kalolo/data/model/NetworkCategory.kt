package com.guychokalolo.epam_internship_android_kalolo.data.model
import com.google.gson.annotations.SerializedName


data class NetworkCategory (
                     @SerializedName("categories")
                     val categoryItems: List<NetworkCategoryItems>
)





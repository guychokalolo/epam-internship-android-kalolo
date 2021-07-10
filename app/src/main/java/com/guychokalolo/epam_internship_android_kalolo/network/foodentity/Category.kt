package com.guychokalolo.epam_internship_android_kalolo.network.foodentity
import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("categories")
    val categoryItems: List<CategoryItem>
)



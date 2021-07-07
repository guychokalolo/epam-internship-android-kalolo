package com.guychokalolo.epam_internship_android_kalolo.network.foodentity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryItems (
    @SerializedName("idCategory")
    val idCategory: String,

    @SerializedName("strCategory")
    val strCategory: String,

    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String,

    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    var isActive : Boolean = false
    )

package com.guychokalolo.epam_internship_android_kalolo.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class NetworkCategoryItems (
    @SerializedName("idCategory")
    val idCategory: Int,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
)

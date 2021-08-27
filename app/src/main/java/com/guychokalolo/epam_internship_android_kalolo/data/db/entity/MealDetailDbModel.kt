package com.guychokalolo.epam_internship_android_kalolo.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealDetailList")
data class MealDetailDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "area")
    val area: String,
    @ColumnInfo(name = "youtube")
    val youtube: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: String
)

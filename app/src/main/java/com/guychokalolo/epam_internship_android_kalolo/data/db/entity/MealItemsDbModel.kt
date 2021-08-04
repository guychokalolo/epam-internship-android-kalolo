package com.guychokalolo.epam_internship_android_kalolo.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealItems")
data class MealItemsDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "mealName")
    val name: String,
    @ColumnInfo(name = "mealImage")
    val image: String
)

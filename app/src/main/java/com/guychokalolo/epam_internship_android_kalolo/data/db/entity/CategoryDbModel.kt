package com.guychokalolo.epam_internship_android_kalolo.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
class CategoryDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "Id")
    val id: Int,
    @ColumnInfo(name = "CategoryName")
    val name: String,
    @ColumnInfo(name = "CategoryImage")
    val image: String,
    @ColumnInfo(name = "CategoryDescription")
    val description: String
)
package com.guychokalolo.epam_internship_android_kalolo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealItemsDbModel
import io.reactivex.rxjava3.core.*


@Dao
interface MealDao {
    @Query("SELECT * FROM mealItems WHERE mealName = :categoryName")
    fun getAllDbMealItems(categoryName: String): Single<List<MealItemsDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDbMealItems(mealItem : List<MealItemsDbModel>)
}
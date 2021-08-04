package com.guychokalolo.epam_internship_android_kalolo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.*
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealDetailDbModel

@Dao
interface MealDetailDao {
    @Query("SELECT * FROM MealDetailList WHERE id =:mealId")
    fun getAllDbMealDetail(mealId: Int): Single<MealDetailDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDbMealDetail(mealDetail: MealDetailDbModel)
}
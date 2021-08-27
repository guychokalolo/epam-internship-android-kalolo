package com.guychokalolo.epam_internship_android_kalolo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.*
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.CategoryDbModel

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category ORDER BY id ASC")
    fun getAllDbCategory(): Single<List<CategoryDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDbCategory(category: List<CategoryDbModel>)
}
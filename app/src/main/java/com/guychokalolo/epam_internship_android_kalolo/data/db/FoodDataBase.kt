package com.guychokalolo.epam_internship_android_kalolo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guychokalolo.epam_internship_android_kalolo.data.db.dao.CategoryDao
import com.guychokalolo.epam_internship_android_kalolo.data.db.dao.MealDetailDao
import com.guychokalolo.epam_internship_android_kalolo.data.db.dao.MealDao
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.CategoryDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealDetailDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealItemsDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.typeconverters.CategoryListConverter


@Database(entities = [
    CategoryDbModel::class,
    MealItemsDbModel::class,
    MealDetailDbModel::class], version = 1, exportSchema = false)
@TypeConverters(CategoryListConverter::class)
abstract class FoodDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao
    abstract fun geMealDao(): MealDao
    abstract fun getMealDetailDao(): MealDetailDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FoodDataBase? = null

        fun getInstanceDb(context: Context): FoodDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDataBase::class.java,
                        "food_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.guychokalolo.epam_internship_android_kalolo.domain.repository
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealDetailEntity
import io.reactivex.rxjava3.core.*

interface MealDetailRepository {
    fun getMealDetail(mealId: Int) : Single<MealDetailEntity>
}
package com.guychokalolo.epam_internship_android_kalolo.domain.useCase

import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealDetailEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.repository.MealDetailRepository
import io.reactivex.rxjava3.core.*


class GetMealDetailUseCase(private val repository: MealDetailRepository) {
    fun getMealDetailUseCase(mealId: Int): Single<MealDetailEntity> =
        repository.getMealDetail(mealId)

}
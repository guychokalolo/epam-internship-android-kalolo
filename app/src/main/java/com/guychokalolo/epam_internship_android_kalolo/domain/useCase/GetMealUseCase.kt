package com.guychokalolo.epam_internship_android_kalolo.domain.useCase

import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealItemsEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.repository.MealRepository
import io.reactivex.rxjava3.core.*


class GetMealUseCase(private val repository: MealRepository) {
    fun mealItemsUseCase(categoryName: String) : Single<List<MealItemsEntity>>{
        return repository.getMealItemsList(categoryName)
    }
}
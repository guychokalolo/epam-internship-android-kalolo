package com.guychokalolo.epam_internship_android_kalolo.domain.repository

import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealItemsEntity
import io.reactivex.rxjava3.core.*

interface CategoryRepository {
    fun getCategoryList(): Single<List<CategoryEntity>>
}
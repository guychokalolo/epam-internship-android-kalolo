package com.guychokalolo.epam_internship_android_kalolo.domain.useCase

import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.*

class GetCategoryUseCase(
    private val repository: CategoryRepository,
) {
    fun categoryUseCase(): Single<List<CategoryEntity>> {
        return repository.getCategoryList()
    }
}
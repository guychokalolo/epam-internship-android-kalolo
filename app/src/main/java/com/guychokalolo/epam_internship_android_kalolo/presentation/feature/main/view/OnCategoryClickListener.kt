package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.CategoryDbModel
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.CategoryEntityUIModel

interface OnCategoryClickListener {
    fun onCategoryClick(item: CategoryEntityUIModel)
}
package com.guychokalolo.epam_internship_android_kalolo.presentation.mapper


import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealDetailEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealItemsEntity
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.CategoryEntityUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealDetailUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealEntityUIModel


/**map CategoryEntity  to ui model**/
fun List<CategoryEntity>.asCategoryUIModel(): List<CategoryEntityUIModel> {
    return map {
        CategoryEntityUIModel(
            id = it.id,
            name = it.name,
            image = it.image,
            description = it.description
        )
    }
}

/**map MealEntity  to ui model**/
fun List<MealItemsEntity>.asMealUIModel(): List<MealEntityUIModel> {
    return map {
        MealEntityUIModel(
            id = it.id,
            name = it.name,
            image = it.image
        )
    }
}

/**map MealDetailEntity  to ui model**/
fun MealDetailEntity.asMealDetailUIModel(): MealDetailUIModel {
    return MealDetailUIModel(
        id = id,
        name = name,
        area = area,
        youtube = youtube,
        imageUrl = imageUrl,
        ingredients = ingredients
    )
}


package com.guychokalolo.epam_internship_android_kalolo.presentation.mapper

import androidx.lifecycle.Transformations.map
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.CategoryDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealDetailDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealItemsDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.model.MealDetailModel
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkCategory
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkMeal
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealDetailEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealItemsEntity
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.CategoryEntityUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealDetailUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealEntityUIModel

// la classe Entity il doit mapper notre classe Db
fun CategoryDbModel.toCategoryEntity() =
    CategoryEntity(id, name, image, description)

/**Map DatabaseCategory to domain entity**/
fun List<CategoryDbModel>.asCategoryEntity(): List<CategoryEntity> {
    return map {
        CategoryEntity(
            id = it.id,
            name = it.name,
            image = it.image,
            description = it.description
        )
    }
}

/**Convert CategoryNetwork results to CategoryDatabase objects**/
fun NetworkCategory.asCategoryDbModel(): List<CategoryDbModel> {
    return this.categoryItems.map {
        CategoryDbModel(
            id = it.idCategory,
            name = it.strCategory,
            image = it.strCategoryThumb,
            description = it.strCategoryDescription
        )
    }
}

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

/**Map MealItemsDatabase to domain entity**/
fun List<MealItemsDbModel>.asMealItemsEntity(): List<MealItemsEntity> {
    return map {
        MealItemsEntity(
            id = it.id,
            name = it.name,
            image = it.image
        )
    }
}

/**Convert MealNetwork results to MealDatabase objects**/
fun NetworkMeal.asMealItemsDbModel(): List<MealItemsDbModel> {
    return this.mealItems.map {
        MealItemsDbModel(
            id = it.idMeal,
            name = it.strMeal,
            image = it.strMealThumb
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

/**map MealDetailModel to MealDetailDatabase**/
fun MealDetailModel.asMealDetailModelDb(): MealDetailDbModel {
    return MealDetailDbModel(
            id = id,
            name = name,
            area = area,
            youtube = youtube,
            imageUrl = imageUrl,
            ingredients = ingredients)

}

/**Map MealItemsDatabase to domain entity**/
fun MealDetailDbModel.asMealDetailEntity(): MealDetailEntity {
    return MealDetailEntity(
            id = id,
            name = name,
            area = area,
            youtube = youtube,
            imageUrl = imageUrl,
            ingredients = ingredients
        )

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


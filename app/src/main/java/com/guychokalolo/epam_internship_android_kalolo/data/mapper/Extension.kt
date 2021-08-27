package com.guychokalolo.epam_internship_android_kalolo

import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.CategoryDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealDetailDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.MealItemsDbModel
import com.guychokalolo.epam_internship_android_kalolo.data.model.*
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealDetailEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealItemsEntity

/**Convert MealNetwork results to MealDetailModel objects**/
fun NetworkMealDetailList.asMealDetailModel(): MealDetailModel {

    return MealDetailModel(
        id = id,
        name = name,
        area = area,
        youtube = youtube,
        imageUrl = imageUrl,
        ingredients = listOf(
            ingredient1 to measure1,
            ingredient2 to measure2,
            ingredient3 to measure3,
            ingredient4 to measure4,
            ingredient5 to measure5,
            ingredient6 to measure6,
            ingredient7 to measure7,
            ingredient8 to measure8,
            ingredient9 to measure9,
            ingredient10 to measure10,
            ingredient11 to measure11,
            ingredient12 to measure12,
            ingredient13 to measure13,
            ingredient14 to measure14,
            ingredient15 to measure15,
            ingredient15 to measure15,
            ingredient16 to measure16,
            ingredient17 to measure17,
            ingredient18 to measure18,
            ingredient19 to measure19,
            ingredient20 to measure20,

            ).filter { pair -> pair.first.isNotEmpty() }
            .filter { pair -> pair.second.isNotEmpty() }
            .map { pair -> pair.first + " " + pair.second }
            .reduceRight { pass, gre -> gre.plus("\n").plus(pass) })
}

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




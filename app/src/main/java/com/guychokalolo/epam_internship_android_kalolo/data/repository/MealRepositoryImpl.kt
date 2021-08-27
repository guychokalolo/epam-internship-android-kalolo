package com.guychokalolo.epam_internship_android_kalolo.data.repository

import com.guychokalolo.epam_internship_android_kalolo.asMealItemsDbModel
import com.guychokalolo.epam_internship_android_kalolo.asMealItemsEntity
import com.guychokalolo.epam_internship_android_kalolo.data.db.dao.MealDao
import com.guychokalolo.epam_internship_android_kalolo.data.network.NetworkApi
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealItemsEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.repository.MealRepository
import io.reactivex.rxjava3.core.Single

class MealRepositoryImpl(
    private val api: NetworkApi,
    private val mealDao: MealDao
) : MealRepository {
    override fun getMealItemsList(categoryName: String): Single<List<MealItemsEntity>> {
        return mealDao.getAllDbMealItems(categoryName)
            .flatMap { localeDb ->
                if (localeDb.isEmpty()) {
                    api.getMealList(categoryName)
                        .map { responseNetwork ->
                            responseNetwork.asMealItemsDbModel()
                        }.flatMap { responseNetworkAsDb ->
                            mealDao.insertDbMealItems(responseNetworkAsDb)
                            Single.just(responseNetworkAsDb)
                        }
                } else {
                    Single.just(localeDb)
                }
            }.map { localDbAsEntity ->
                localDbAsEntity.asMealItemsEntity()
            }
    }

}
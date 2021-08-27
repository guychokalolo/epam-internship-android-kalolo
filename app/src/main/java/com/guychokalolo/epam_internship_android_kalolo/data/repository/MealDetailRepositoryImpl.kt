package com.guychokalolo.epam_internship_android_kalolo.data.repository

import androidx.room.rxjava3.EmptyResultSetException
import com.guychokalolo.epam_internship_android_kalolo.asMealDetailEntity
import com.guychokalolo.epam_internship_android_kalolo.data.db.dao.MealDetailDao
import com.guychokalolo.epam_internship_android_kalolo.data.network.NetworkApi
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.MealDetailEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.repository.MealDetailRepository
import com.guychokalolo.epam_internship_android_kalolo.asMealDetailModel
import com.guychokalolo.epam_internship_android_kalolo.asMealDetailModelDb
import io.reactivex.rxjava3.core.Single

class MealDetailRepositoryImpl(
    private val api: NetworkApi,
    private val mealDetailDao: MealDetailDao
) : MealDetailRepository {
    override fun getMealDetail(mealId: Int): Single<MealDetailEntity> {
        return mealDetailDao.getAllDbMealDetail(mealId).onErrorResumeNext {
            if (it is EmptyResultSetException) {
                api.getMealDetailList(mealId)
                    .map { responseNetwork ->
                        responseNetwork.mealDetailList.first().asMealDetailModel()
                    }.map { responseNetworkAsDm ->
                        responseNetworkAsDm.asMealDetailModelDb()
                    }.flatMap { responseNetworkAsDb ->
                        mealDetailDao.insertDbMealDetail(responseNetworkAsDb)
                        Single.just(responseNetworkAsDb)
                    }
            }else{
                    Single.error(it)
        }
    }.map { it.asMealDetailEntity() }
}
}
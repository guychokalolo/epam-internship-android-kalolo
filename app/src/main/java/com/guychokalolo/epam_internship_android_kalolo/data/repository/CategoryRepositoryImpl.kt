package com.guychokalolo.epam_internship_android_kalolo.data.repository

import com.guychokalolo.epam_internship_android_kalolo.asCategoryDbModel
import com.guychokalolo.epam_internship_android_kalolo.asCategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.data.db.dao.CategoryDao
import com.guychokalolo.epam_internship_android_kalolo.data.network.NetworkApi
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single


class CategoryRepositoryImpl(
    private val api: NetworkApi,
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun getCategoryList(): Single<List<CategoryEntity>> {
        return categoryDao.getAllDbCategory()
            .flatMap { localeDb ->
                if (localeDb.isEmpty()) {
                    api.getCategoryList()
                        .map { responseNetwork ->
                            responseNetwork.asCategoryDbModel()
                        }
                        .flatMap { responseNetworkAsDb ->
                            categoryDao.insertDbCategory(responseNetworkAsDb)
                            Single.just(responseNetworkAsDb)
                        }
                } else {
                    Single.just(localeDb)
                }
            }.map { localDbAsEntity ->
                localDbAsEntity.asCategoryEntity()
            }
    }
}


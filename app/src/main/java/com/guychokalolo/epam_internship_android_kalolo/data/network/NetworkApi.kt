package com.guychokalolo.epam_internship_android_kalolo.data.network

import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkCategory
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkMeal
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkMealDetail
import io.reactivex.rxjava3.core.*
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("categories.php")
    fun getCategoryList(): Single<NetworkCategory>

    @GET("filter.php")
    fun getMealList(@Query("c") categoryItemName: String): Single<NetworkMeal>

    @GET("lookup.php")
    fun getMealDetailList(@Query("i") mealId: Int): Single<NetworkMealDetail>
}
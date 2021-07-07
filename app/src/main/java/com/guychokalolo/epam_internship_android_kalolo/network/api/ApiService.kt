package com.guychokalolo.epam_internship_android_kalolo.network.api

import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Category
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.CategoryItems
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Meal
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    fun getCategoryList() : Call<Category>

    @GET("filter.php")
    fun getMealList(@Query ("c")categoryItemName: String) : Call<Meal>

    @GET("lookup.php")
    fun getMealDetailList(@Query ("i")categoryItemName: Int) : Call<MealDetail>


}
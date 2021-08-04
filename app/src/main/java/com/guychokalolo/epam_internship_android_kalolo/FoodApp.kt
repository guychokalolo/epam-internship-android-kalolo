package com.guychokalolo.epam_internship_android_kalolo

import android.app.Application
import com.guychokalolo.epam_internship_android_kalolo.common.Constants
import com.guychokalolo.epam_internship_android_kalolo.data.db.FoodDataBase
import com.guychokalolo.epam_internship_android_kalolo.data.network.NetworkApi
import com.guychokalolo.epam_internship_android_kalolo.data.repository.CategoryRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FoodApp : Application() {

    val database by lazy { FoodDataBase.getInstanceDb(this) }

    val retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(NetworkApi::class.java)

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object{
         lateinit var INSTANCE: FoodApp
    }
}
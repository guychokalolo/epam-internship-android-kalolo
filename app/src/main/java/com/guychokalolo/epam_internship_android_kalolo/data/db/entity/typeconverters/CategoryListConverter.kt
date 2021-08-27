package com.guychokalolo.epam_internship_android_kalolo.data.db.entity.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CategoryListConverter {
    @TypeConverter
    fun stringToList(flatStringList: String): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(flatStringList, listType)

    }

    @TypeConverter
    fun listToString(listOfString: List<String>): String {
        return Gson().toJson(listOfString)
    }
}
package com.guychokalolo.epam_internship_android_kalolo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.guychokalolo.epam_internship_android_kalolo.fragments.MealListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inject the Fragment  into the activity
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,MealListFragment())
                //.add(R.id.fragment_container, CategoryFoodFragment())
                .commit()
    }

}
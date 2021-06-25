package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.guychokalolo.epam_internship_android_kalolo.CategoryImageModel
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.adapter.CategoryFoodAdapter
import java.util.ArrayList

class CategoryFoodFragment : Fragment() {

    private lateinit var horizontalRecyclerView : RecyclerView
    private val categoryFoodAdapter = CategoryFoodAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.all_category_food, container, false)

        horizontalRecyclerView = view.findViewById(R.id.horizontal_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        horizontalRecyclerView.adapter = categoryFoodAdapter

        categoryFoodAdapter.setListImage(listOf(
                CategoryImageModel(R.drawable.category_pizza1),
                CategoryImageModel(R.drawable.burger3),
                CategoryImageModel(R.drawable.chicken3),
                CategoryImageModel(R.drawable.tacos1),
                CategoryImageModel(R.drawable.shawarma3)))
    }

}
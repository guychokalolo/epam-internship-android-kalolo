package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guychokalolo.epam_internship_android_kalolo.CategoryImageModel
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.adapter.CategoryFoodAdapter

class CategoryFoodFragment : Fragment() {

    private val categoryFoodAdapter = CategoryFoodAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.all_category_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recyclerview)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecyclerView.adapter = categoryFoodAdapter

        categoryFoodAdapter.setListImage(listOf(
                CategoryImageModel(R.drawable.category_pizza1),
                CategoryImageModel(R.drawable.burger3),
                CategoryImageModel(R.drawable.chicken3),
                CategoryImageModel(R.drawable.tacos1),
                CategoryImageModel(R.drawable.shawarma3)))
    }

}
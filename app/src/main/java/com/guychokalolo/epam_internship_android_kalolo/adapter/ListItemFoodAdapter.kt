package com.guychokalolo.epam_internship_android_kalolo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guychokalolo.epam_internship_android_kalolo.FoodModel
import com.guychokalolo.epam_internship_android_kalolo.MealListActivity
import com.guychokalolo.epam_internship_android_kalolo.OnFoodClickListener
import com.guychokalolo.epam_internship_android_kalolo.R

class ListItemFoodAdapter(private val listFood: List<FoodModel>,
                          private val onFoodClickListener: OnFoodClickListener) : RecyclerView.Adapter<ListItemFoodAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImage: ImageView = view.findViewById<ImageView>(R.id.image_item)
        val item_name : TextView? = view.findViewById(R.id.item_name)
        val item_description : TextView?= view.findViewById(R.id.item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentFood = listFood[position]

        holder.item_name?.text  = currentFood.name
        holder.item_description?.text = currentFood.description
        holder.foodImage.setImageResource(currentFood.imageFood)

        holder.itemView.setOnClickListener { onFoodClickListener.onFoodItemClicked(currentFood) }
    }
}
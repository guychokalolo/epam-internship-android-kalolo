package com.guychokalolo.epam_internship_android_kalolo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guychokalolo.epam_internship_android_kalolo.OnMealClickListener
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems

class MealListAdapter(private val onMealClickListener: OnMealClickListener)
    : RecyclerView.Adapter<MealListAdapter.ViewHolder>(){

    var listMeal : MutableList<MealItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMeal.size

    }

    fun setMealItem(list : List<MealItems>){
        this.listMeal.clear()
        this.listMeal.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFood = listMeal[position]
        holder.bind(currentFood)
        holder.itemView.setOnClickListener { onMealClickListener.onFoodItemClicked(currentFood) }
        // customizer corner radius
        if (position == 0){
            holder.container.setBackgroundResource(R.drawable.round_corner_top)
        }

        if (position == listMeal.size.minus(1)){
            holder.container.setBackgroundResource(R.drawable.round_corner_bottom)
            holder.divider.visibility = View.GONE
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImage: ImageView = view.findViewById(R.id.image_item)
        val itemTitle : TextView? = view.findViewById(R.id.item_name)
        val container : ConstraintLayout = view.findViewById(R.id.item_food_container)
        val divider : View = view.findViewById(R.id.divider)

        fun bind(meal: MealItems){
            itemTitle?.text = meal.strMeal
            Glide.with(itemView.context).load(meal.strMealThumb).into(foodImage)
        }
    }
}
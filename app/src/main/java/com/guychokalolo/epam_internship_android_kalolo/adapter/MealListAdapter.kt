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
import com.guychokalolo.epam_internship_android_kalolo.databinding.ItemFoodBinding
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems

class MealListAdapter(private val onMealClickListener: OnMealClickListener)
    : RecyclerView.Adapter<MealListAdapter.MealListViewHolder>(){

    var listMeal : MutableList<MealItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MealListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMeal.size
    }

    fun setMealItem(list : List<MealItems>){
        this.listMeal.clear()
        this.listMeal.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        val currentFood = listMeal[position]
        holder.bind(currentFood, onMealClickListener)
    }

    class MealListViewHolder(private val  binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: MealItems, onMealClickListener: OnMealClickListener){
            binding.itemName.text = meal.strMeal
            Glide.with(itemView.context).load(meal.strMealThumb).into(binding.imageItem)

            itemView.setOnClickListener { onMealClickListener.onFoodItemClicked(meal) }

            if (adapterPosition == 0){
                binding.itemFoodContainer.setBackgroundResource(R.drawable.round_corner_top)
            }

            if (adapterPosition == meal.idMeal.count().minus(1)){
                binding.itemFoodContainer.setBackgroundResource(R.drawable.round_corner_bottom)
                binding.divider.visibility = View.GONE
            }
        }
    }
}
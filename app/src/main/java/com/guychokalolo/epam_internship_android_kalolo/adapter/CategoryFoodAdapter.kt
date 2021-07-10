package com.guychokalolo.epam_internship_android_kalolo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.`interface`.OnCategoryClickListener
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.CategoryItem

class CategoryFoodAdapter(private var categoryNameItems : OnCategoryClickListener) : RecyclerView.Adapter<CategoryFoodAdapter.CategoryViewHolder>(){

    private val categoryItems : MutableList<CategoryItem> =  mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_food, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItems[position], categoryNameItems)
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    fun setListImage(list: List<CategoryItem>){
        this.categoryItems.clear()
        this.categoryItems.addAll(list)
        notifyDataSetChanged()
    }

    class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val imageFood : ImageView = view.findViewById(R.id.item_category_image)
        private val cardViewContainer : CardView = view.findViewById(R.id.cardView_container)

        fun bind(categoryItems: CategoryItem, onCategoryClickListener: OnCategoryClickListener){
            Glide.with(itemView.context).load(categoryItems.strCategoryThumb).into(imageFood)

            itemView.setOnClickListener {
                onCategoryClickListener.onCategoryClick(categoryItems)

                cardViewContainer.isSelected = !cardViewContainer.isSelected
                if(categoryItems.selectedCategory){

                    cardViewContainer.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.salmon))
                }else{
                    cardViewContainer.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colombia_blue))
                }

            }
        }
    }
}
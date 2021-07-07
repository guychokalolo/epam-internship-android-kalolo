package com.guychokalolo.epam_internship_android_kalolo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.`interface`.OnItemCategory
import com.guychokalolo.epam_internship_android_kalolo.fragments.MealListFragment
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.CategoryItems

class CategoryFoodAdapter() : RecyclerView.Adapter<CategoryFoodAdapter.MyViewHolder>(){

    private val listImage : MutableList<CategoryItems> =  mutableListOf()
    private var selectedItem = 0
    private var categoryNameItems : OnItemCategory?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_food, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.itemView.context
        val categoryItem = (listImage[position])
        holder.bind(listImage[position] )
        // item selected background color changed
        holder.cardViewContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colombia_blue))
        if (selectedItem == position){
            holder.cardViewContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.salmon))
        }

        holder.itemView.setOnClickListener {
            val previousItem = selectedItem
            selectedItem = position
            notifyItemChanged(previousItem)
            notifyItemChanged(position)
            categoryNameItems?.onClickCategory(categoryItem)
        }
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    fun setCategoryName(cat : OnItemCategory){
        categoryNameItems = cat
    }

    fun setListImage(list: List<CategoryItems>){
        this.listImage.clear()
        this.listImage.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val imageFood : ImageView = view.findViewById(R.id.item_category_image)
        val cardViewContainer : CardView = view.findViewById(R.id.cardView_container)

        fun bind(categoryItems: CategoryItems){
            Glide.with(itemView.context).load(categoryItems.strCategoryThumb).into(imageFood)
        }
    }



}
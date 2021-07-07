package com.guychokalolo.epam_internship_android_kalolo.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.guychokalolo.epam_internship_android_kalolo.CategoryImageModel
import com.guychokalolo.epam_internship_android_kalolo.MainActivity
import com.guychokalolo.epam_internship_android_kalolo.R
import java.security.AccessController.getContext

class CategoryFoodAdapter : RecyclerView.Adapter<CategoryFoodAdapter.MyViewHolder>()  {

    private val listImage : MutableList<CategoryImageModel> =  mutableListOf()
    private var selectedItem = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_food, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.bind(listImage[position])
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
        }
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    fun setListImage(list : List<CategoryImageModel>){
        this.listImage.clear()
        this.listImage.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val imageFood : ImageView? = view.findViewById(R.id.item_category_image)
        val cardViewContainer : CardView = view.findViewById(R.id.cardView_container)

        fun bind(model:CategoryImageModel){
            imageFood?.setImageResource(model.imageCategoryFood)
        }
    }
}
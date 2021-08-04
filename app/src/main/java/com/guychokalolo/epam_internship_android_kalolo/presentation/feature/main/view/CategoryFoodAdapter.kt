package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guychokalolo.epam_internship_android_kalolo.data.db.entity.CategoryDbModel
import com.guychokalolo.epam_internship_android_kalolo.databinding.ItemCategorySelectedBinding
import com.guychokalolo.epam_internship_android_kalolo.domain.entity.CategoryEntity
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.CategoryEntityUIModel

class CategoryFoodAdapter(private var categoryNameItems: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryFoodAdapter.CategoryViewHolder>() {

    private val categoryItems: MutableList<CategoryEntityUIModel> = mutableListOf()
    private var currentSelection = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategorySelectedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val function = { pos: Int ->
            if (currentSelection != pos) {
                currentSelection = pos
                notifyDataSetChanged()
            }
        }
        holder.bind(
            categoryItems[position],
            position,
            position == currentSelection,
            categoryNameItems,
            function
        )
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    fun setListImage(list: List<CategoryEntityUIModel>) {
        this.categoryItems.clear()
        this.categoryItems.addAll(list)
        notifyDataSetChanged()
    }

    class CategoryViewHolder(private val binding: ItemCategorySelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            category: CategoryEntityUIModel,
            position: Int,
            selected: Boolean,
            onCategoryClickListener: OnCategoryClickListener,
            function: (Int) -> Unit
        ) {

            binding.root.isSelected = selected
            Glide.with(itemView.context).load(category.image).into(binding.imageCategory)

            binding.root.setOnClickListener {
                onCategoryClickListener.onCategoryClick(category)
                function(position)
            }

        }

    }
}

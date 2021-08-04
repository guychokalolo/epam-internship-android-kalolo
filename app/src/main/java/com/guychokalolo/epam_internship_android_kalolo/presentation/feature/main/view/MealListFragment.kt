package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.guychokalolo.epam_internship_android_kalolo.FoodApp
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.databinding.FragmentMealListBinding
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkCategoryItems
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkMealItems
import com.guychokalolo.epam_internship_android_kalolo.data.repository.CategoryRepositoryImpl
import com.guychokalolo.epam_internship_android_kalolo.data.repository.MealRepositoryImpl
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetCategoryUseCase
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetMealUseCase
import com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel.CategoryViewModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel.CategoryViewModelFactory
import com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel.MealViewModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel.MealViewModelFactory
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.CategoryEntityUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealEntityUIModel


class MealListFragment : Fragment(), OnMealClickListener, OnCategoryClickListener {

    private val mealListAdapter = MealListAdapter(this)
    private val categoryFoodAdapter = CategoryFoodAdapter(this)
    private var listCategory: List<CategoryEntityUIModel>? = null
    lateinit var bindig: FragmentMealListBinding
    private val categoryViewModel: CategoryViewModel by viewModels {
        CategoryViewModelFactory(
            GetCategoryUseCase(
                CategoryRepositoryImpl(
                    (FoodApp.INSTANCE.retrofit),
                    FoodApp.INSTANCE.database.getCategoryDao()
                )
            )
        )
    }
    private val mealViewModel: MealViewModel by viewModels {
        MealViewModelFactory(
            GetMealUseCase(
                MealRepositoryImpl(
                    (FoodApp.INSTANCE.retrofit), FoodApp.INSTANCE.database.geMealDao()
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindig = FragmentMealListBinding.inflate(layoutInflater)
        bindig.toolbar.inflateMenu(R.menu.nav_menu)
        return bindig.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecycler()
        loadCategories()
    }

    private fun setupRecycler() {
        bindig.horizontalRecyclerview.adapter = categoryFoodAdapter
        bindig.listItemFoodRecyclerview.adapter = mealListAdapter
    }

    // Load Local Category
    private fun loadCategories() {
        categoryViewModel.startCategory()
        categoryViewModel.categoryEntityUiModel.observe(viewLifecycleOwner, {
            categoryFoodAdapter.setListImage(it)
        })
    }

    // Load Local Meal
    private fun loadMeal(name: String) {
        mealViewModel.startMeal(name)
        mealViewModel.meal.observe(viewLifecycleOwner) {
            mealListAdapter.setMealItem(it)
        }
    }

    override fun onFoodItemClicked(mealItems: MealEntityUIModel) {
        findNavController().navigate(R.id.mealDetailsFragment, bundleOf("KEY_ID" to mealItems.id))
    }

    override fun onCategoryClick(item: CategoryEntityUIModel) {
        loadMeal(item.name)
    }
}


package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guychokalolo.epam_internship_android_kalolo.OnMealClickListener
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.`interface`.OnCategoryClickListener
import com.guychokalolo.epam_internship_android_kalolo.adapter.CategoryFoodAdapter
import com.guychokalolo.epam_internship_android_kalolo.adapter.MealListAdapter
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Category
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.CategoryItem
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Meal
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems
import com.guychokalolo.epam_internship_android_kalolo.network.retrofitclient.FoodApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers



class MealListFragment : Fragment(), OnMealClickListener , OnCategoryClickListener{

    private val mealListAdapter = MealListAdapter(this)
    private val categoryFoodAdapter = CategoryFoodAdapter(this)
    private var listCategory = listOf<CategoryItem>()
    private val DEFAULT_CATEGORY_POSITION = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recyclerview)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecyclerView.adapter = categoryFoodAdapter

        val listFoodRecyclerView = view.findViewById<RecyclerView>(R.id.list_item_food_recyclerview)
        listFoodRecyclerView.adapter = mealListAdapter
        mealListAdapter.notifyDataSetChanged()
        loadCategories()
    }

    private fun loadCategories() {
        FoodApi.retrofitService.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Category>{
                    override fun onNext(category: Category) {
                        categoryFoodAdapter.setListImage(category.categoryItems)
                        listCategory = category.categoryItems
                        loadMeal(listCategory[DEFAULT_CATEGORY_POSITION])
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(view?.context, e.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onComplete() {
                        // hide progress indicator.
                    }
                    override fun onSubscribe(d: Disposable) {
                        // start showing progress indicator.
                    }

                })
    }

    private fun loadMeal(categoryName : CategoryItem) {
        val categoryItemName = categoryName.strCategory
        FoodApi.retrofitService.getMealList(categoryItemName).map {
            it.mealItems
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {response -> mealListAdapter.setMealItem(response)},
                {error -> Toast.makeText(view?.context, error.message, Toast.LENGTH_SHORT ).show()})

    }

    override fun onFoodItemClicked(mealItems: MealItems) {
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MealDetailsFragment.getFragment(mealItems.idMeal))
                .addToBackStack(null)
                .commit()
    }

    override fun onCategoryClick(item: CategoryItem) {
        loadMeal(item)
    }
}



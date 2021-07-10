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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



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

    private fun loadCategories(){
        FoodApi.retrofitService.getCategoryList().enqueue(object : Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {

                response.body()?.categoryItems?.let { categoryFoodAdapter.setListImage(it)}
                listCategory = response.body()!!.categoryItems
                loadMeal(listCategory[DEFAULT_CATEGORY_POSITION])
                categoryFoodAdapter.notifyDataSetChanged()
                Log.e("Category", "Response loadCategories")
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(view?.context, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Category", "onFailure")
            }
        })
    }

    fun loadMeal(categoryName : CategoryItem){
        val name = categoryName.strCategory
        FoodApi.retrofitService.getMealList(name).enqueue(object : Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                mealListAdapter.setMealItem(response.body()!!.mealItems)
                Log.e("Category", "Response loadMeal")
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                Toast.makeText(view?.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
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
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
import com.guychokalolo.epam_internship_android_kalolo.`interface`.OnItemCategory
import com.guychokalolo.epam_internship_android_kalolo.adapter.CategoryFoodAdapter
import com.guychokalolo.epam_internship_android_kalolo.adapter.MealListAdapter
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Category
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.CategoryItems
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Meal
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems
import com.guychokalolo.epam_internship_android_kalolo.network.retrofitclient.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealListFragment : Fragment(), OnMealClickListener{

    val mealListAdapter = MealListAdapter(this)
    val categoryFoodAdapter = CategoryFoodAdapter()
    var list = listOf<CategoryItems>()

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
        getCategory()

        categoryFoodAdapter.setCategoryName(categoryName)
    }

    private fun getCategory(){
        FoodApi.retrofitService.getCategoryList().enqueue(object : Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {

                Log.e("Category", "Response Category")
                categoryFoodAdapter.setListImage(response.body()!!.categoryItems)
                list = response.body()!!.categoryItems
                getMeal(list[0])
                categoryFoodAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(view?.context, t.message, Toast.LENGTH_SHORT).show()

                Log.e("Category", "onFailure")
            }
        })
    }

    private val categoryName = object : OnItemCategory {
        override fun onClickCategory(categoryNameItem: CategoryItems) {
            getMeal(categoryNameItem)
        }
    }

    fun getMeal(categoryName : CategoryItems){
        val name = categoryName.strCategory
        FoodApi.retrofitService.getMealList(name).enqueue(object : Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                //Log.e("Category", " Name Category : $name")

                mealListAdapter.setMealItem(response.body()!!.mealItems)
                Log.e("Category", "Response MealListFragment")
                mealListAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                Toast.makeText(view?.context, t.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onFoodItemClicked(mealItems: MealItems) {
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MealDetailsFragment.getFragment(mealItems))
                .addToBackStack(null)
                .commit()
    }


}
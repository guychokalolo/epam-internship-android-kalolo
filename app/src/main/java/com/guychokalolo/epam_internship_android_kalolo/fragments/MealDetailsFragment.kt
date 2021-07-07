package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Meal
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealDetail
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems
import com.guychokalolo.epam_internship_android_kalolo.network.retrofitclient.FoodApi
import com.guychokalolo.epam_internship_android_kalolo.toMealDetailUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsFragment : Fragment() {

    private lateinit var  btn : ImageButton
    private lateinit var imageFood: ImageView
    private lateinit var nameFood : TextView
    private lateinit var descriptionFood: TextView
    private lateinit var subIngredient: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn = view.findViewById(R.id.imageToolbarBack)
        imageFood = view.findViewById(R.id.imageMeal)
        nameFood = view.findViewById(R.id.subhead)
        descriptionFood = view.findViewById(R.id.headline)
        subIngredient = view.findViewById(R.id.subIngredient)

        btn.setOnClickListener{ finish() }

        FoodApi.retrofitService.getMealDetailList(requireArguments().getInt(KEY_ID)).enqueue(object : Callback<MealDetail>{
            override fun onResponse(call: Call<MealDetail>, response: Response<MealDetail>) {
                val mealResponse = response.body()?.mealDetailList?.first()?.toMealDetailUIModel()
                Log.e("Category MealDetail", "Response")

                nameFood.text = mealResponse?.name
                descriptionFood.text = mealResponse?.area
                subIngredient.text = mealResponse?.ingredients
                Glide.with(view.context).load(mealResponse?.imageUrl).into(imageFood)
            }

            override fun onFailure(call: Call<MealDetail>, t: Throwable) {
                Toast.makeText(view.context, t.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun finish(){
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object{
        private const val KEY_ID = "KEY_ID"
        fun getFragment(mealItems: MealItems): MealDetailsFragment{
            return MealDetailsFragment().apply {
                arguments = bundleOf(KEY_ID to mealItems.idMeal)

            }
        }
    }

}
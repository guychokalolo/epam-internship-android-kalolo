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
import com.guychokalolo.epam_internship_android_kalolo.databinding.FragmentDetailScreenBinding
import com.guychokalolo.epam_internship_android_kalolo.databinding.FragmentMealDetailsBinding
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.Meal
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealDetail
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealItems
import com.guychokalolo.epam_internship_android_kalolo.network.retrofitclient.FoodApi
import com.guychokalolo.epam_internship_android_kalolo.toMealDetailUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailScreenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBack.setOnClickListener{ finish() }

        requireArguments().getString(KEY_ID)?.let {
            FoodApi.retrofitService.getMealDetailList(it).enqueue(object : Callback<MealDetail>{
                override fun onResponse(call: Call<MealDetail>, response: Response<MealDetail>) {
                    val mealResponse = response.body()?.mealDetailList?.first()?.toMealDetailUIModel()
                    Log.e("Category MealDetail", "Response")

                    binding.subhead.text = mealResponse?.name
                    binding.headline.text = mealResponse?.area
                    binding.subIngredient.text = mealResponse?.ingredients
                    Glide.with(view.context).load(mealResponse?.imageUrl).into(binding.imageMeal)
                }

                override fun onFailure(call: Call<MealDetail>, t: Throwable) {
                    Toast.makeText(view.context, t.message,Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun finish(){
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object{
        private const val KEY_ID = "KEY_ID"
        fun getFragment(mealItems: String): MealDetailsFragment{
            return MealDetailsFragment().apply {
                arguments = bundleOf(KEY_ID to mealItems)

            }
        }
    }

}
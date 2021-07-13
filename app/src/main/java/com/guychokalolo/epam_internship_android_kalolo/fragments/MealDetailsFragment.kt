package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.guychokalolo.epam_internship_android_kalolo.databinding.FragmentDetailScreenBinding
import com.guychokalolo.epam_internship_android_kalolo.network.foodentity.MealDetail
import com.guychokalolo.epam_internship_android_kalolo.network.retrofitclient.FoodApi
import com.guychokalolo.epam_internship_android_kalolo.toMealDetailUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


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
            FoodApi.retrofitService.getMealDetailList(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        response ->
                        binding.subhead.text = response.mealDetailList.first().toMealDetailUIModel().name
                        binding.headline.text = response.mealDetailList.first().toMealDetailUIModel().area
                        binding.subIngredient.text = response.mealDetailList.first().toMealDetailUIModel().ingredients
                        Glide.with(view.context).load(response.mealDetailList.first().toMealDetailUIModel().imageUrl).into(binding.imageMeal)
                    },
                    {error -> Toast.makeText(view.context, error.message, Toast.LENGTH_SHORT ).show()})
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
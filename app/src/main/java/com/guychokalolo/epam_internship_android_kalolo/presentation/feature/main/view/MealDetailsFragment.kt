package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.guychokalolo.epam_internship_android_kalolo.FoodApp
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.data.repository.MealDetailRepositoryImpl
import com.guychokalolo.epam_internship_android_kalolo.databinding.FragmentDetailScreenBinding
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetMealDetailUseCase
import com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel.MealDetailViewModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel.MealDetailViewModelFactory
import io.reactivex.rxjava3.disposables.Disposable

class MealDetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailScreenBinding
    private val mealDetailViewModel: MealDetailViewModel by viewModels {
        MealDetailViewModelFactory(
            GetMealDetailUseCase(
                MealDetailRepositoryImpl(
                    (FoodApp.INSTANCE.retrofit),
                    FoodApp.INSTANCE.database.getMealDetailDao()
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBack.setOnClickListener { finish() }
        initUi()
    }

    private fun initUi() {
        requireArguments().getInt(KEY_ID).let { mealDetailViewModel.startMealDetail(it) }
        mealDetailViewModel.mealDetail.observe(viewLifecycleOwner, {
            binding.subhead.text = it.name
            binding.headline.text = it.area
            binding.subIngredient.text = it.ingredients
            view?.let { view ->
                Glide.with(view.context).load(it.imageUrl).into(binding.imageMeal)
            }
        })
    }

    private fun finish() {
        findNavController().navigate(R.id.action_mealDetailsFragment_to_mealListFragment)
    }

    companion object {
        private const val KEY_ID = "KEY_ID"
        fun getFragment(mealItems: Int): MealDetailsFragment {
            return MealDetailsFragment().apply {
                arguments = bundleOf(KEY_ID to mealItems)
            }
        }
    }
}
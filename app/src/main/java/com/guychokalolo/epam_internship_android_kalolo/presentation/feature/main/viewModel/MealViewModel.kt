package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetMealUseCase
import com.guychokalolo.epam_internship_android_kalolo.presentation.mapper.asMealUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealEntityUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable


class MealViewModel(private val getMealItemsUseCase: GetMealUseCase): ViewModel() {

    private val mutableMeal: MutableLiveData<List<MealEntityUIModel>> = MutableLiveData()
    val meal: LiveData<List<MealEntityUIModel>>
    get() = mutableMeal
    private val compositeDisposable = CompositeDisposable()


    fun startMeal(name: String ){
        getMealItemsUseCase.mealItemsUseCase(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       mutableMeal.value = it.asMealUIModel()
            },{
                it.printStackTrace()
            })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
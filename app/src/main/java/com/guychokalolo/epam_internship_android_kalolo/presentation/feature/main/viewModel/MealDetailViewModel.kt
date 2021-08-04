package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guychokalolo.epam_internship_android_kalolo.data.model.NetworkMealDetail
import com.guychokalolo.epam_internship_android_kalolo.domain.userCase.GetMealDetailUseCase
import com.guychokalolo.epam_internship_android_kalolo.presentation.mapper.asMealDetailUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.MealDetailUIModel
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MealDetailViewModel(private val getMealDetailUseCase: GetMealDetailUseCase) : ViewModel() {

    private val mutableMealDetail: MutableLiveData<MealDetailUIModel> = MutableLiveData()
    val mealDetail: LiveData<MealDetailUIModel>
        get() = mutableMealDetail
    private val compositeDisposable = CompositeDisposable()

    fun startMealDetail(mealId: Int) {
        getMealDetailUseCase.getMealDetailUseCase(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableMealDetail.value = it.asMealDetailUIModel()
            },
                {
                    it.printStackTrace()
                })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
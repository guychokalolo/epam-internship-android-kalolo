package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guychokalolo.epam_internship_android_kalolo.domain.useCase.GetCategoryUseCase
import com.guychokalolo.epam_internship_android_kalolo.presentation.mapper.asCategoryUIModel
import com.guychokalolo.epam_internship_android_kalolo.presentation.model.CategoryEntityUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable


class CategoryViewModel(private val getCategoryUseCase: GetCategoryUseCase) : ViewModel() {

    private val mutableCategoryEntityUiModel: MutableLiveData<List<CategoryEntityUIModel>> =
        MutableLiveData()

    val categoryEntityUiModel: LiveData<List<CategoryEntityUIModel>>
        get() = mutableCategoryEntityUiModel

    private val compositeDisposable = CompositeDisposable()

    fun startCategory() {
        getCategoryUseCase.categoryUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableCategoryEntityUiModel.value = it.asCategoryUIModel()
                Log.e("CategoryEntity", "$it")
            }, {
                it.printStackTrace()
            })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
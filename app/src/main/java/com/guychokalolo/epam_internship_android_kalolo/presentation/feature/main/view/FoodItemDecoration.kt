package com.guychokalolo.epam_internship_android_kalolo.presentation.feature.main.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FoodItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = 20
    }
}
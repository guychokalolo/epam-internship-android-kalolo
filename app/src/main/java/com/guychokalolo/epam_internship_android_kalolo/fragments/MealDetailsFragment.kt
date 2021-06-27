package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.guychokalolo.epam_internship_android_kalolo.R

class MealDetailsFragment : Fragment() {

    private lateinit var  btn : ImageButton
    private lateinit var imageFood: ImageView
    private lateinit var nameFood : TextView
    private lateinit var descriptionFood: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn = view.findViewById(R.id.button_back)
        imageFood = view.findViewById(R.id.image_food)
        nameFood = view.findViewById(R.id.name_food)
        descriptionFood = view.findViewById(R.id.description_food)
        // getArguments for MealListFragment
        nameFood.text = arguments?.getString(KEY_NAME)
        descriptionFood.text = arguments?.getString(KEY_DESCRIPTION)
        arguments?.getInt(KEY_IMAGE,0)?.let { imageFood.setImageResource(it) }
        btn.setOnClickListener{ finish() }
    }

    private fun finish(){
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object{
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private const val KEY_IMAGE = "KEY_IMAGE"
        fun getFragment(nameFood : String, descriptionFood: String, imageFood : Int): MealDetailsFragment{
            return MealDetailsFragment().apply {
                arguments = bundleOf(KEY_NAME to nameFood,
                                            KEY_DESCRIPTION to descriptionFood,
                                            KEY_IMAGE to imageFood)
            }
        }
    }

}
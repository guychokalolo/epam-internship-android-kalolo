package com.guychokalolo.epam_internship_android_kalolo

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MealDetailsActivity : AppCompatActivity() {

    private val btn : ImageButton by lazy {findViewById(R.id.button_back) }
    private val imageFood: ImageView by lazy { findViewById(R.id.image_food) }
    private  val nameFood: TextView by lazy { findViewById(R.id.name_food) }
    private  val descriptionFood: TextView by lazy {findViewById(R.id.description_food) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        nameFood.text = intent.getStringExtra(KEY_NAME).orEmpty()
        descriptionFood.text = intent.getStringExtra(KEY_DESCRIPTION).orEmpty()
        imageFood.setImageResource(intent.getIntExtra(KEY_IMAGE,0))

        btn.setOnClickListener{ finish() }
    }

    companion object{
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private const val KEY_IMAGE = "KEY_IMAGE"
        fun getIntent(context : Context, nameFood : String, descriptionFood: String, imageFood : Int): Intent{
            val newIntent = Intent(context, MealDetailsActivity::class.java)
            newIntent.putExtra(KEY_NAME, nameFood)
            newIntent.putExtra(KEY_DESCRIPTION, descriptionFood)
            newIntent.putExtra(KEY_IMAGE, imageFood)
            return newIntent
        }
    }
}
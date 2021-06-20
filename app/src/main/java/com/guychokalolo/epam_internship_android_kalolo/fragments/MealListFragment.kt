package com.guychokalolo.epam_internship_android_kalolo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.guychokalolo.epam_internship_android_kalolo.FoodModel
import com.guychokalolo.epam_internship_android_kalolo.MainActivity
import com.guychokalolo.epam_internship_android_kalolo.OnFoodClickListener
import com.guychokalolo.epam_internship_android_kalolo.R
import com.guychokalolo.epam_internship_android_kalolo.adapter.FoodItemDecoration
import com.guychokalolo.epam_internship_android_kalolo.adapter.ListItemFoodAdapter

class MealListFragment : Fragment(), OnFoodClickListener {

    private lateinit var listFoodRecyclerView: RecyclerView
    private  var myListFood = arrayListOf<FoodModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_meal_list, container, false)

        listFoodRecyclerView = view.findViewById(R.id.list_item_food_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerview(myListFood)
        listFood()
    }

    private fun initRecyclerview(list: ArrayList<FoodModel>){
        listFoodRecyclerView.adapter = ListItemFoodAdapter(list, this)
        listFoodRecyclerView.addItemDecoration(FoodItemDecoration())
    }

    private fun listFood(){
        myListFood.add(FoodModel("Pizza МАРГАРИТА", "Томатный пицца-соус, томаты черри", R.drawable.image_pizza1))
        myListFood.add(FoodModel("Pizza ГРИБНАЯ", "Чесночный соус, нежная ветчина", R.drawable.image_pizza2))
        myListFood.add(FoodModel("Pizza ЦЫПЛЕНОК РЭНЧ", "Популярный американский соус \"Рэнч\"", R.drawable.image_pizza3))
        myListFood.add(FoodModel("Pizza 4 СЫРА", "Увеличенная порция сыра моцарелла", R.drawable.image_pizza4))
        myListFood.add(FoodModel("Pizza СУПРИМ", "Пикантные колбаски пепперони", R.drawable.image_pizza5))
        myListFood.add(FoodModel("Pizza МЕХИКО", "Кусочки поджаренного куриного филе", R.drawable.image_pizza6))
        myListFood.add(FoodModel("Pizza ПЕППЕРОНИ", "ОЧЕНЬ много пикантных колбасок пепперони", R.drawable.image_pizza7))
        myListFood.add(FoodModel("Pizza ВЕГЕТАРИАНСКАЯ", "Сыр моцарелла, сладкий перец", R.drawable.image_pizza8))
        myListFood.add(FoodModel("Pizza БРЕЦЕЛЬ И ШПИНАТ", "Сливочный чесночный соус с пармезаном", R.drawable.image_pizza9))
    }

    override fun onFoodItemClicked(foodModel: FoodModel) {
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MealDetailsFragment.getFragment(foodModel.name, foodModel.description, foodModel.imageFood))
                .addToBackStack(null)
                .commit()
    }
}
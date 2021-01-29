package fr.isen.bernard.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.bernard.androidrestaurant.data.Dish
import fr.isen.bernard.androidrestaurant.data.Ingredients
import fr.isen.bernard.androidrestaurant.databinding.ActivityDishDetailBinding
import fr.isen.bernard.androidrestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityDishDetailBinding;

class DishDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        binding = ActivityDishDetailBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as Dish
        dish.getAllPictures()?.let {
            binding.detailPager.adapter = DetailViewAdapter(this, it)
        }

        for (ing in dish.ingredients){
            binding.textIng.append(" • " + ing.name + "\n")
            //println(ing.name)
        }
        /*
        Picasso.get()
            .load(dish.pictures.firstOrNull())
            .into(binding.imageDish)

         */
        //val ing = dish.ingredients as Ingredients
        //println("ing " + ing)
        setTitle(dish.title)
        //binding.textIng = dish
        //holder.textIng = dish
    }
}
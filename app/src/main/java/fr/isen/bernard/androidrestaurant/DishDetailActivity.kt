package fr.isen.bernard.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.text.toInt
import android.view.View
import android.widget.Toast
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

        var oldQty = 0
        binding.add.setOnClickListener() {
            oldQty = binding.qty.text.toString().toInt()
            oldQty += 1
            binding.qty.text = oldQty.toString()
            updateTotalPrice(dish)
        }

        binding.priceU.text = dish.getFormatedPrice()

        binding.sup.setOnClickListener() {
            oldQty = binding.qty.text.toString().toInt()
            if (oldQty > 0){
                oldQty -= 1
                binding.qty.text = oldQty.toString()
                updateTotalPrice(dish)
            } else {
                Toast.makeText(this, "Cannot order less than 0 item, smartass", Toast.LENGTH_SHORT).show();
            }
        }



        for (ing in dish.ingredients){
            binding.textIng.append(" • " + ing.name + "\n")
        }

        setTitle(dish.title)
    }

    fun updateTotalPrice(dish: Dish){
        binding.orderBtn.text = "Let's GO ! " + (binding.qty.text.toString().toInt() * dish.getPrice()) + " $"
    }
}
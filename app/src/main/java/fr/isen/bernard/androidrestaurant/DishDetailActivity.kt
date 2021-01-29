package fr.isen.bernard.androidrestaurant

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.bernard.androidrestaurant.data.CartItem
import fr.isen.bernard.androidrestaurant.data.Dish
import fr.isen.bernard.androidrestaurant.databinding.ActivityDishDetailBinding
import java.io.File
import java.text.DecimalFormat

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

        binding.orderBtn.setOnClickListener() {
            saveToCart(dish, binding.qty.text.toString().toInt())
        }

        setTitle(dish.title)
    }

    fun updateTotalPrice(dish: Dish){
        binding.orderBtn.text = "Let's GO ! " + DecimalFormat("#,###.00").format(binding.qty.text.toString().toInt() * dish.getPrice()).toString() + " $"
    }


    fun saveToCart(dish: Dish, qty: Int) {
        val FILE_NAME: String = "/cart.json"

        val file = File(cacheDir.absolutePath + FILE_NAME)
        println(cacheDir.absolutePath + FILE_NAME)

        if (file.exists()) {
            val json = Gson().fromJson(file.readText(), CartItem::class.java)
            json.qty = qty
            json.dish = dish
            val test = GsonBuilder().create().toJson(json)
            file.writeBytes(test.toByteArray())
        } else {
            val jsonObj = Gson().toJson(CartItem(dish, qty))
            file.writeText(jsonObj)
        }

    }
}
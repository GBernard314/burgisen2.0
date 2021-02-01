package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.data.CartItem
import fr.isen.bernard.androidrestaurant.data.Dish
import fr.isen.bernard.androidrestaurant.databinding.ActivityDishDetailBinding
import java.io.File
import java.text.DecimalFormat

private lateinit var binding: ActivityDishDetailBinding;

class DishDetailActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.menu) {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent);
            return true
        }
        return super.onOptionsItemSelected(item)

    }

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
        var alreadyIn = false

        val file = File(cacheDir.absolutePath + FILE_NAME)
        if (file.exists()) {
            val file_text = file.readText()
            val json = Gson().fromJson(file_text, Cart::class.java)
            //if the item is already in the cart
            for (jItem in json.items) {
                if (dish.id == jItem.dish.id) {
                    jItem.qty += qty
                    alreadyIn = true
                }
            }
            //if we did not find it
            //we add it
            if (alreadyIn == false) {
                json.items += CartItem(dish, qty)
            }
            saveDishCount(json)
            var jsonObj = Gson().toJson(json)
            file.writeText(jsonObj.toString())
        } else {
            val newCart = Cart(listOf(CartItem(dish, qty)))
            saveDishCount(newCart)
            val jsonObj = Gson().toJson(newCart)
            file.writeText(jsonObj.toString())
        }
    }

    fun getTotalQty(cart: Cart): Int{
        var tot = 0
        for (item in cart.items){
            tot += item.qty
        }
        return tot
    }


    private fun saveDishCount(cart: Cart) {
        val count = getTotalQty(cart)
        val sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        sharedPreferences.edit().putInt(CART_COUNT, count).apply()
        println("tot = " + sharedPreferences.getInt("cart_count", 0))
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val CART_FILE = "user_cart.json"
        const val CART_COUNT = "cart_count"
    }
}
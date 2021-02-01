package fr.isen.bernard.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.databinding.ActivityCartBinding
import java.io.File
import java.text.DecimalFormat

lateinit var binding_cart: ActivityCartBinding

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_cart = ActivityCartBinding.inflate(layoutInflater)
        setTitle("Cart")
        setContentView(binding_cart.root)
        //binding.EntreeTitle.text = intent.getStringExtra("category")

        binding_cart.recyclerViewCart.layoutManager = LinearLayoutManager(this)
        listCart()
        //val reqRet = makeRequest()

        binding_cart.emptyCart.setOnClickListener {
            emptyCart()
            startActivity(Intent(this, CartActivity::class.java));
        }

        binding_cart.checkOutBtn.text = "Checkout " + getPriceCart()


    }

    fun emptyCart() {
        val FILE_NAME: String = "/cart.json"

        val file = File(cacheDir.absolutePath + FILE_NAME)
        if (file.exists()) {
            val newCart = Cart(emptyList())
            val jsonObj = Gson().toJson(newCart)
            file.writeText(jsonObj.toString())
            saveDishCount(newCart)
        }
    }

    fun listCart(): Cart{
        val FILE_NAME: String = "/cart.json"

        val file = File(cacheDir.absolutePath + FILE_NAME)
        var newCart = Cart(emptyList())
        if (file.exists()) {
            val file_text = file.readText()
            val json = Gson().fromJson(file_text, Cart::class.java)
            newCart = json
        }

        binding_cart.recyclerViewCart.adapter = CartRecycleViewAdapter(newCart.items, this);
        return newCart
    }

    fun getPriceCart(): String {
        val cart = listCart()
        var price = 0
        for (item in cart.items) {
            price += item.dish.getPrice().toInt() * item.qty
        }

        if (price == 0) {
            return "0.00 $"
        }

        val dec = DecimalFormat("#,###.00")

        return dec.format(price.toDouble()).toString() + " $"
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
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val CART_FILE = "user_cart.json"
        const val CART_COUNT = "cart_count"
    }

}
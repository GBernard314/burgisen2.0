package fr.isen.bernard.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.data.CartItem
import fr.isen.bernard.androidrestaurant.databinding.ActivityCartBinding
import fr.isen.bernard.androidrestaurant.databinding.ActivityDessertBinding
import java.io.File

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
}
package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.Cart
import java.io.File

class CheckOutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        if (checkCredentials()){
            val cart: Cart = intent.getSerializableExtra("order") as Cart
            val intent = Intent(this, OrderedActivity::class.java)
            intent.putExtra("order", cart)
            startActivity(intent)
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }



    }

    private fun checkCredentials(): Boolean {
        val sharedPreferences = getSharedPreferences(CheckOutActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.contains(USER_ID)
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

    private fun saveDishCount(cart: Cart) {
        val count = getTotalQty(cart)
        val sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        sharedPreferences.edit().putInt(CART_COUNT, count).apply()
    }

    fun getTotalQty(cart: Cart): Int{
        var tot = 0
        for (item in cart.items){
            tot += item.qty
        }
        return tot
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val USER_ID = "user_id"
        const val USER_ADDR = "user_addr"
        const val CART_COUNT = "cart_count"
    }

}
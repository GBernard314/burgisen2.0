package fr.isen.bernard.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.databinding.ActivityOrderedBinding
import fr.isen.bernard.androidrestaurant.databinding.ActivitySignInBinding

lateinit var binding_ordered: ActivityOrderedBinding

class OrderedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordered)
        binding_ordered = ActivityOrderedBinding.inflate(layoutInflater);

        //val address = intent.getStringExtra("address")
        val cart: Cart = intent.getSerializableExtra("order") as Cart
        binding_ordered.address.text = readCredentialsAddress()
    }


    private fun readCredentialsAddress(): String {
        val sharedPreferences = getSharedPreferences(OrderedActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.getString(USER_ADDR, "void street").toString()
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val USER_ID = "user_id"
        const val USER_ADDR = "user_addr"
    }

}
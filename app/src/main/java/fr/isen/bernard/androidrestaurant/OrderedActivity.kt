package fr.isen.bernard.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.bernard.androidrestaurant.databinding.ActivityOrderedBinding
import fr.isen.bernard.androidrestaurant.databinding.ActivitySignInBinding

lateinit var binding_ordered: ActivityOrderedBinding

class OrderedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordered)
        binding_ordered = ActivityOrderedBinding.inflate(layoutInflater);


        val address = intent.getStringExtra("address")
        binding_ordered.address.text = address
    }
}
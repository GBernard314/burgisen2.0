package fr.isen.bernard.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.bernard.androidrestaurant.databinding.ActivityStarterBinding

private lateinit var binding: ActivityStarterBinding

class StarterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarterBinding.inflate(layoutInflater);
        setContentView(binding.root);
        //binding.EntreeTitle.text = intent.getStringExtra("category");

        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = StarterRecycleViewAdapter(listOf("Julien","Pierre", "Paul"));

    }
}
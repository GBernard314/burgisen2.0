package fr.isen.desoomer.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.desoomer.androidrestaurant.databinding.ActivityStarterBinding

private lateinit var binding: ActivityStarterBinding

class StarterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarterBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.starterTitle.text = intent.getStringExtra("category");

        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = StarterRecycleViewAdapter(listOf("Julien","Pierre", "Paul"));

    }
}
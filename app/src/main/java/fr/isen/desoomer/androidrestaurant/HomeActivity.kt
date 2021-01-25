package fr.isen.desoomer.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import fr.isen.desoomer.androidrestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding;

class HomeActivity : AppCompatActivity() {
    public fun displayMsg(str: String) {
        Toast.makeText(this, "Clicked : " + str, Toast.LENGTH_SHORT).show();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root);

        /*
        binding.starterButton.setOnClickListener {
            val intent = Intent(this, StarterActivity::class.java)
            intent.putExtra("category", "Starter")
            startActivity(intent);
            displayMsg("Starter Button");
        }

        binding.dishButton.setOnClickListener {
            val intent = Intent(this, DishActivity::class.java)
            intent.putExtra("category", "Dish")
            startActivity(intent);
            displayMsg("Dish Button");
        }

        binding.dessertButton.setOnClickListener{
            val intent = Intent(this, DessertActivity::class.java)
            intent.putExtra("category", "Dessert")
            startActivity(intent);
            displayMsg("Dessert Button");
        }
        */
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Home Activity Destroyed");
    }

}
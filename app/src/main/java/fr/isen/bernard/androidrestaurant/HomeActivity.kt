package fr.isen.bernard.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import fr.isen.bernard.androidrestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding;

class HomeActivity : BaseActivity() {
    public fun displayMsg(str: String) {
        Toast.makeText(this, "Clicked : " + str, Toast.LENGTH_SHORT).show();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root);
        setTitle("BURG\'ISEN")


        binding.buttonEntrees.setOnClickListener {
            val intent = Intent(this, StarterActivity::class.java)
            intent.putExtra("category", "Starter")
            startActivity(intent);
            displayMsg("Starter Button");
        }
        binding.buttonPlats.setOnClickListener {
            val intent = Intent(this, DishActivity::class.java)
            intent.putExtra("category", "Dish")
            startActivity(intent);
            displayMsg("Dish Button");
        }

        binding.buttonDesserts.setOnClickListener{
            val intent = Intent(this, DessertActivity::class.java)
            intent.putExtra("category", "Dessert")
            startActivity(intent);
            displayMsg("Dessert Button");
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        println("Home Activity Destroyed");
    }

}
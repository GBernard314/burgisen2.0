 package fr.isen.bernard.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.DishDetailData
import fr.isen.bernard.androidrestaurant.databinding.ActivityStarterBinding
import org.json.JSONException
import org.json.JSONObject

 private lateinit var binding: ActivityStarterBinding

class StarterActivity : AppCompatActivity() {


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
        binding = ActivityStarterBinding.inflate(layoutInflater)
        setTitle("Starters")
        setContentView(binding.root) 
        //binding.EntreeTitle.text = intent.getStringExtra("category") 

        binding.starterList.layoutManager = LinearLayoutManager(this)
        val reqRet = makeRequest()


    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue((this))
        var url = "http://test.api.catering.bluecodegames.com/menu"
        var postData = JSONObject()
        var jsonRet = ""
        try {
            postData.put("id_shop", "1")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {  response ->
                val gson: DishDetailData =
                    Gson().fromJson(response.toString(), DishDetailData::class.java)
                gson.data.firstOrNull { it.category == "Entrées" }?.dish?.let {
                    binding.starterList.adapter = StarterRecycleViewAdapter(it, this);
                }
            },
            { error ->
                error.printStackTrace()
            })
        queue.add(jsonObjectRequest)
    }

}
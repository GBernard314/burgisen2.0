 package fr.isen.bernard.androidrestaurant

import android.os.Bundle
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

class StarterActivity : BaseActivity() {


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
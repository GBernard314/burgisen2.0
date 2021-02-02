package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.DishDetailData
import fr.isen.bernard.androidrestaurant.databinding.ActivitySignInBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding_sign_in: ActivitySignInBinding;

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        binding_sign_in = ActivitySignInBinding.inflate(layoutInflater);
        setContentView(binding_sign_in.root);

        binding_sign_in.toRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding_sign_in.connection.setOnClickListener{
            signin()
            //TODO if that doesn't work
        }
    }
    private fun signin() {
        val pass = binding_sign_in.editTextTextPassword
        val email = binding_sign_in.editTextTextPassword
        val queue = Volley.newRequestQueue((this))
        var url = "http://test.api.catering.bluecodegames.com/user/login"
        var postData = JSONObject()
        var jsonRet = ""
        try {
            postData.put("id_shop", "1").put("email", email).put("password", pass)
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
                /*
                gson.data.firstOrNull { it.category == "Entrées" }?.dish?.let {
                    binding.starterList.adapter = StarterRecycleViewAdapter(it, this);
                }
                 */
            },
            { error ->
                error.printStackTrace()
            })

        println(jsonObjectRequest)
        queue.add(jsonObjectRequest)
    }


}
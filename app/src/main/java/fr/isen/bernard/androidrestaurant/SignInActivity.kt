package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.RequestApi
import fr.isen.bernard.androidrestaurant.databinding.ActivitySignInBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

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
        }
    }
    private fun signin() {
        val pass = binding_sign_in.editTextTextPassword.text.toString()
        val email = binding_sign_in.editTextTextEmailAddress.text.toString()
        val queue = Volley.newRequestQueue((this))
        var url = "http://test.api.catering.bluecodegames.com/user/login"
        var postData = JSONObject()
        try {
            postData.put("id_shop", "1")
                .put("email", email)
                .put("password", pass)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {  response ->
                val reqApiGson: RequestApi =
                    Gson().fromJson(response.toString(), RequestApi::class.java)
                println(reqApiGson.toString())
                if (reqApiGson.code == "200"){
                    Toast.makeText(this, "Welcome back " + reqApiGson.fields.firstname + " " + reqApiGson.fields.lastname, Toast.LENGTH_SHORT).show();
                    saveCredentials(pass, reqApiGson.fields.address)
                    val intent = Intent(this, OrderedActivity::class.java)
                    intent.putExtra("address", reqApiGson.fields.address)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid Email / Password combo", Toast.LENGTH_SHORT).show();
                }
            },
            { error ->
                onErrorResponse(error)
                error.printStackTrace()
            })

        queue.add(jsonObjectRequest)
    }

    fun onErrorResponse(error: VolleyError): Boolean {
        var body: String = ""
        //get status code here
        val statusCode = error.networkResponse.statusCode.toString()
        //get response body and parse with appropriate encoding
        if (error.networkResponse.data != null) {
            try {
                body = String(error.networkResponse.data)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        println(body)
        return body == ""
        //do stuff with the body...
    }


    private fun saveCredentials(pass: String, addr: String) {
        val sharedPreferences = getSharedPreferences(RegisterActivity.APP_PREFS, MODE_PRIVATE)
        sharedPreferences.edit().putString(RegisterActivity.USER_ID, pass).apply()
        sharedPreferences.edit().putString(RegisterActivity.USER_ADDR, addr).apply()
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val USER_ID = "user_id"
        const val USER_ADDR = "user_addr"
    }
}
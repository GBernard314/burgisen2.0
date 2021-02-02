package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.DishDetailData
import fr.isen.bernard.androidrestaurant.databinding.ActivityRegisterBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException


lateinit var binding_register: ActivityRegisterBinding

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        binding_register = ActivityRegisterBinding.inflate(layoutInflater);
        setContentView(binding_register.root);

        binding_register.toSignin.setOnClickListener {
            //todo redirect if the account exists
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding_register.register.setOnClickListener {
            //todo redirect if the account exists
            register()
            startActivity(Intent(this, OrderedActivity::class.java))
        }
    }
    private fun register() {
        val pass = binding_register.editTextTextPassword.text
        val email = binding_register.editTextTextEmailAddress.text
        val fname = binding_register.editTextTextName.text
        val lname = binding_register.editTextTextSurname.text
        val add =  binding_register.editTextTextAddressNo.text.toString() + " " + binding_register.editTextTextAddress.text.toString() + " " + binding_register.editTextTextAddressZip.text.toString()
        println(add)
        val queue = Volley.newRequestQueue((this))
        var url = "http://test.api.catering.bluecodegames.com/user/register"
        var postData = JSONObject()
        var jsonRet = ""
        try {
            postData.put("id_shop", "1")
                .put("email", email)
                .put("password", pass)
                .put("firstname", fname)
                .put("lastname", lname)
                .put("address", add)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {  response ->
                /*
                val gson: DishDetailData =
                    Gson().fromJson(response.toString(), DishDetailData::class.java)
                gson.data.firstOrNull { it.category == "Entrées" }?.dish?.let {
                    binding.starterList.adapter = StarterRecycleViewAdapter(it, this);
                }
                 */
            },
            { error ->
                onErrorResponse(error)
                error.printStackTrace()
            })

        println(jsonObjectRequest)
        queue.add(jsonObjectRequest)
    }

    fun onErrorResponse(error: VolleyError) {
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
        //do stuff with the body...
    }

}
package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.data.RequestApi
import fr.isen.bernard.androidrestaurant.databinding.ActivityOrderedBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.UnsupportedEncodingException
import java.text.DecimalFormat

lateinit var binding_ordered: ActivityOrderedBinding

class OrderedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordered)
        binding_ordered = ActivityOrderedBinding.inflate(layoutInflater);
        setContentView(binding_ordered.root)

        //val address = intent.getStringExtra("address")
        val cart: Cart = intent.getSerializableExtra("order") as Cart
        val address = readCredentialsAddress()
        println(address)
        binding_ordered.addText.text = address

        var dishList: String = ""
        var priceTot: Double = 0.0
        for (item in cart.items){
            dishList += " [ " + item.qty.toString() + " ] - " + item.dish.title + "\n"
            priceTot += item.qty * item.dish.getPrice()
        }

        binding_ordered.dishes.text = dishList
        binding_ordered.textView4.text = "Total : " + getFormatedPrice(priceTot)
        pushOrder()

    }
    private fun pushOrder() {
        val queue = Volley.newRequestQueue((this))
        var url = "http://test.api.catering.bluecodegames.com/user/order"
        var postData = JSONObject()
        val cart: Cart = intent.getSerializableExtra("order") as Cart
        val idUser = readCredentialsId()
        println(serializeCart())
        try {
            postData.put("id_shop", "1")
                .put("id_user", idUser)
                .put("msg", serializeCart())

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {  response ->
                /*
                val reqApiGson: RequestApi =
                    Gson().fromJson(response.toString(), RequestApi::class.java)
                if (reqApiGson.code == "200") {
                    Toast.makeText(this, "Welcome onboard " + reqApiGson.fields.firstname + " " + reqApiGson.fields.lastname, Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, OrderedActivity::class.java)
                    intent.putExtra("address", reqApiGson.fields.address)
                    startActivity(intent)
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

    fun getFormatedPrice(price: Double): String {
        val dec = DecimalFormat("#,###.00")
        var str = dec.format(price).toString()
        return str + " $"
    }

    private fun readCredentialsAddress(): String {
        val sharedPreferences = getSharedPreferences(OrderedActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.getString(USER_ADDR, "void street").toString()
    }

    private fun readCredentialsId(): String {
        val sharedPreferences = getSharedPreferences(OrderedActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.getString(USER_ID, "0").toString()
    }

    fun serializeCart(): String{
        val FILE_NAME: String = "/cart.json"

        val file = File(cacheDir.absolutePath + FILE_NAME)
        var newCart = Cart(emptyList())
        if (file.exists()) {
            val file_text = file.readText()
            val json = Gson().fromJson(file_text, Cart::class.java)
            newCart = json
            var str: String = file_text
            str = file_text.replace("\"", "\\\"")
            return "\"" + str + "\""
        }

        binding_cart.recyclerViewCart.adapter = CartRecycleViewAdapter(newCart.items, this);
        return "{}"
    }


    companion object {
        const val APP_PREFS = "app_prefs"
        const val USER_ID = "user_id"
        const val USER_ADDR = "user_addr"
    }

}
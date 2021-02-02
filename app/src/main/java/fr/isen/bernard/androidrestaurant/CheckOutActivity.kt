package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Bundle

class CheckOutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        if (checkCredentials()){

            val intent = Intent(this, OrderedActivity::class.java)
            intent.putExtra("order", intent.getStringExtra("order"))
            startActivity(Intent(this, OrderedActivity::class.java))
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }



    }

    private fun checkCredentials(): Boolean {
        val sharedPreferences = getSharedPreferences(CheckOutActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.contains(USER_ID)
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val USER_ID = "user_id"
        const val USER_ADDR = "user_addr"
    }

}
package fr.isen.bernard.androidrestaurant

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity() : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val cart = menu?.findItem(R.id.menu);
        cart?.setActionView(R.layout.cart_card)
        val notifCount = cart?.getActionView()

        val sharedPreferences = getSharedPreferences(DishDetailActivity.APP_PREFS, MODE_PRIVATE)
        var cartQuantity = sharedPreferences.getInt("cart_count", 0)
        val textView = notifCount?.findViewById<TextView>(R.id.actionbar_notifcation_textview)
        textView?.setText("" + cartQuantity)

        cart?.setOnMenuItemClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            true
        }
        return super.onCreateOptionsMenu(menu)
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

}
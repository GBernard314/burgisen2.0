package fr.isen.bernard.androidrestaurant

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.data.CartItem
import fr.isen.bernard.androidrestaurant.databinding.CartCardBinding
import java.io.File

class CartRecycleViewAdapter(
    private val dataSet: List<CartItem>,
    private val ct: Context
):
    RecyclerView.Adapter<CartRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(binding: CartCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.DishTitle
        var quantity = binding.dishQty
        val image = binding.DishImage
        val add = binding.add
        val sup = binding.sup
        val container: CardView = binding.root;
        var id = ""
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRecycleViewAdapter.ViewHolder {
        val itemBinding = CartCardBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: CartRecycleViewAdapter.ViewHolder, position: Int) {
        holder.title.text = dataSet[position].dish.title
        holder.quantity.text = dataSet[position].qty.toString()
        holder.id = dataSet[position].dish.id
        //println(dataSet[position].getFormatedPrice())
        //Picasso.get().load(dataSet[position].getFirstPicture()).into(holder.image);
        Picasso.get()
            .load(dataSet[position].dish.getFirstPicture())
            .error(R.drawable.burger_king_logo!!)
            .placeholder(R.drawable.burger_king_logo)// Image to load when something goes wrong
            .into(holder.image);

        holder.add.setOnClickListener{
            updateCartItem(holder.id, "Add")
            holder.quantity.text = increase(holder.quantity.text.toString().toInt()).toString()
            if (holder.quantity.text == "0"){
                ct.startActivity(Intent(ct, CartActivity::class.java))
            }
        }
        holder.sup.setOnClickListener{
            updateCartItem(holder.id, "Sup")
            holder.quantity.text = decrease(holder.quantity.text.toString().toInt()).toString()
            if (holder.quantity.text == "0"){
                ct.startActivity(Intent(ct, CartActivity::class.java))
            }
        }

        //notifyDataSetChanged()
        //Thread.sleep(1000)

        //ct.startActivity(Intent(ct, CartActivity::class.java));

        holder.container.setOnClickListener{
            val intent = Intent(ct, DishDetailActivity::class.java)
            intent.putExtra("dish_product", holder.title.text as String)
            val dish = dataSet[position].dish
            intent.putExtra("dish", dish)
            ct.startActivity(intent);
        }
    }

    fun decrease(int: Int): Int{
        if (int > 0 ){
            return int -1
        } else {
            return int
        }
    }

    fun increase(int: Int): Int{
        return int + 1
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }


    fun updateCartItem(id: String, value: String){
        val FILE_NAME: String = "/cart.json"

        val file = File(ct.cacheDir.absolutePath + FILE_NAME)
        if (file.exists()) {
            val file_text = file.readText()
            val json = Gson().fromJson(file_text, Cart::class.java)
            for (item in json.items){
                if (item.dish.id == id)
                    if (value == "Add"){
                        item.qty = item.qty + 1
                    } else if (value == "Sup"){
                        item.qty = item.qty - 1
                    }
                if (item.qty <= 0){
                   json.items -= item
                }
            }
            val jsonObj = Gson().toJson(json)
            file.writeText(jsonObj.toString())
        }
    }

}
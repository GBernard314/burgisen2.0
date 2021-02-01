package fr.isen.bernard.androidrestaurant

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.bernard.androidrestaurant.data.Cart
import fr.isen.bernard.androidrestaurant.data.CartItem
import fr.isen.bernard.androidrestaurant.data.Dish
import fr.isen.bernard.androidrestaurant.databinding.CartCardBinding

class CartRecycleViewAdapter(
    private val dataSet: List<CartItem>,
    private val ct: Context
):
    RecyclerView.Adapter<CartRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(binding: CartCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.DishTitle
        var quantity = binding.dishQty
        val image = binding.DishImage
        val container: CardView = binding.root;
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
        //println(dataSet[position].getFormatedPrice())
        //Picasso.get().load(dataSet[position].getFirstPicture()).into(holder.image);
        Picasso.get()
            .load(dataSet[position].dish.getFirstPicture())
            .error(R.drawable.burger_king_logo!!)
            .placeholder(R.drawable.burger_king_logo)// Image to load when something goes wrong
            .into(holder.image);


        holder.container.setOnClickListener{
            val intent = Intent(ct, DishDetailActivity::class.java)
            intent.putExtra("dish_product", holder.title.text as String)
            val dish = dataSet[position].dish
            intent.putExtra("dish", dish)
            ct.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

}
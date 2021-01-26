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
import fr.isen.bernard.androidrestaurant.data.Dish
import fr.isen.bernard.androidrestaurant.databinding.CardBinding

class StarterRecycleViewAdapter(
    private val dataSet: List<Dish>,
    private val ct: Context
):
    RecyclerView.Adapter<StarterRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.DishTitle
        val price = binding.DishPrice
        val image = binding.DishImage
        val container: CardView = binding.root;
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StarterRecycleViewAdapter.ViewHolder {
        val itemBinding = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: StarterRecycleViewAdapter.ViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.price.text = dataSet[position].getFormatedPrice()
        println(dataSet[position].getFormatedPrice())
        //Picasso.get().load(dataSet[position].getFirstPicture()).into(holder.image);
        Picasso.get()
            .load(dataSet[position].getFirstPicture())
            .error(R.drawable.burger_king_logo!!)
            .placeholder(R.drawable.burger_king_logo)// Image to load when something goes wrong
            .into(holder.image);


        holder.container.setOnClickListener{
            val intent = Intent(ct, DishDetailActivity::class.java)
            println("Clicked" + position);
            intent.putExtra("dish_product", holder.title.text as String)
            ct.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

}
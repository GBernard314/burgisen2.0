import fr.isen.bernard.androidrestaurant.DishDetailActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bernard.androidrestaurant.data.Dish
import fr.isen.bernard.androidrestaurant.databinding.CardBinding

class DishRecycleViewAdapter(
    private val dataSet: List<Dish>,
    private val ct: Context
):
    RecyclerView.Adapter<DishRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.DishTitle
        val price = binding.DishPrice
        val image = binding.DishImage
        val container: CardView = binding.root;
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DishRecycleViewAdapter.ViewHolder {
        val itemBinding = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: DishRecycleViewAdapter.ViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.price.text = dataSet[position].getPrice().toString()
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
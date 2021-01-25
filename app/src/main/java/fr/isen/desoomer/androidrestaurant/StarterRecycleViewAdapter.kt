package fr.isen.desoomer.androidrestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.desoomer.androidrestaurant.databinding.ActivityStarterBinding
import fr.isen.desoomer.androidrestaurant.databinding.CardBinding

class StarterRecycleViewAdapter(private val dataSet: List<String>):
    RecyclerView.Adapter<StarterRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.starterCardTitle
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StarterRecycleViewAdapter.ViewHolder {
        val itemBinding = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: StarterRecycleViewAdapter.ViewHolder, position: Int) {
        //holder.title.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

}
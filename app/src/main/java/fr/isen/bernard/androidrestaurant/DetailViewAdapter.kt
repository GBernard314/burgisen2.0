package fr.isen.bernard.androidrestaurant

import android.os.Bundle
import android.provider.Settings.Global.putString
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailViewAdapter(activity: AppCompatActivity, val items: List<String>): FragmentStateAdapter(activity){



    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        //val fragment = DetailFragment()//.newInstance(items[position])

        return DetailFragment.newInstance(items[position])
    }
}
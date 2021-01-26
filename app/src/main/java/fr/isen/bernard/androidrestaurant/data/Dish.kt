package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.DecimalFormat

data class Dish(
    @SerializedName("name_fr") val title: String,
    @SerializedName("ingredients") val ingredients: List<Ingredients>,
    @SerializedName("images") val pictures: List<String>,
    @SerializedName("prices") val prices: List<Price>
) : Serializable {

    fun getPrice(): Double {
        return prices[0].price.toDouble()
    }

    fun getFormatedPrice(): String {
        val dec = DecimalFormat("#,###.00")
        var str = dec.format(prices[0].price.toDouble()).toString()
       return str + " $"
    }

    fun getFirstPicture(): String? {
        if(pictures.isNotEmpty() && pictures[0].isNotEmpty()){
            return pictures[0]
        } else {
            return null;
        }
    }

}
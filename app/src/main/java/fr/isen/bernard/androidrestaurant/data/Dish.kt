package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
       return prices[0].price + '€'
    }

    fun getFirstPicture(): String? {
        if(pictures.isNotEmpty() && pictures[0].isNotEmpty()){
            return pictures[0]
        } else {
            return null;
        }
    }

}
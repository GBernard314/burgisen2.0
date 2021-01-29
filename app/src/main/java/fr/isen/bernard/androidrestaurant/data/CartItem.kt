package fr.isen.bernard.androidrestaurant.data

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CartItem(
    @SerializedName("dish") var dish: Dish,
    @SerializedName("qty") var qty: Int
): Serializable  {

}
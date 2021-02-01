package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.*

class Cart(
    @SerializedName("dishes") var items: List<CartItem>
): Serializable
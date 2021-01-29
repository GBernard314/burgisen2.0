package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price(
    @SerializedName("price") val price: String
)  : Serializable {
    var price2: String = "666"
}




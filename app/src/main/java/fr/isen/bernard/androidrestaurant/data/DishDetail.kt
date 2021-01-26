package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName

data class DishDetail (@SerializedName(value = "name_fr") val category: String, @SerializedName(value = "items") val dish: List<Dish>)
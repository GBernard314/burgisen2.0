package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName

data class DishDetailData (@SerializedName(value = "data") var data: List<DishDetail>) {
}

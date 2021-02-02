package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestApiFields(
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: String,
    @SerializedName("address") val address: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String

) : Serializable {
}
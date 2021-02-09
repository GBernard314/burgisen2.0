package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RequestApiUser(
    @SerializedName(value = "firstname") var firstname: String,
    @SerializedName(value="id") var id: Int,
    @SerializedName(value = "lastname") var lastname: String,
    @SerializedName(value = "email") var email: String,
    @SerializedName(value = "address") var address: String,
    @SerializedName(value = "password") var password: String
) : Serializable {
}
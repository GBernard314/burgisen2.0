package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestApi (@SerializedName(value = "data") var fields: List<RequestApiFields>,
                  @SerializedName(value = "code") var code: String
) : Serializable{

}
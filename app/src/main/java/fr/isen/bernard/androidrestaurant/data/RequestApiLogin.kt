package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestApiLogin (@SerializedName(value = "data") var fields: RequestApiUser,
                  @SerializedName(value = "code") var code: String
) : Serializable {
}
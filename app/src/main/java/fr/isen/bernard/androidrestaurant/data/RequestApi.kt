package fr.isen.bernard.androidrestaurant.data

import com.google.gson.annotations.SerializedName

class RequestApi (@SerializedName(value = "data") var fields: RequestApiFields,
                  @SerializedName(value = "code") var code: String
) {

}
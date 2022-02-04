package fr.isen.VILLEMINOT.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Meals(@SerializedName("name_fr") val name: String,
            @SerializedName("images") val images: List<String>,
            @SerializedName("ingredients") val ingredients: List<Ingredients>,
            @SerializedName("prices") val prices: List<Price>
            ): Serializable {
                fun getThumbnailURL(): String? {
                    return if(images.isNotEmpty() && images.first().isNotEmpty()){
                        images.first()
                    }else
                        null
                }
}
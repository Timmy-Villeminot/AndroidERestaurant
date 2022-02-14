package fr.isen.VILLEMINOT.androiderestaurant.basket

import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.VILLEMINOT.androiderestaurant.network.Meals
import java.io.File
import java.io.Serializable

class Basket(val items: MutableList<BasketItem>): Serializable {
    var itemsCount: Int = 0
        get() {

            //compter items
            /*var count = 0
            items.forEach {
                count += it.quantity
            }
            return count*/

            val count = items.map {
                it.quantity
            }.reduceOrNull { acc, i -> acc +i } ?: 0
            return count ?:0

        }

    var totalPrice: Float = 0f
        get() {

            return items.map { item ->
                item.quantity * item.meal.prices.first().price.toFloat()
            }.reduceOrNull { acc, fl -> acc + fl } ?: 0f
        }

    fun addItem(item: Meals, quantity: Int){
        val existingItem = items.firstOrNull{ it.meal.name == item.name }
        existingItem?.let {
            existingItem.quantity += quantity
        } ?: run {
            val basketItem = BasketItem(item, quantity)
            items.add(basketItem)
        }
    }

    fun removeItem(basketItem: BasketItem){
        items.remove(basketItem)
    }

    fun save(context: Context){
        val jsonFile =  File(context.cacheDir.absolutePath + BASKET_FILE)
        val json =  GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        updateCounter(context)
    }

    private fun updateCounter(context: Context){
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemsCount)
        editor.apply()
    }


    companion object{
        fun getBasket(context: Context): Basket {
            val count = Basket(mutableListOf()).itemsCount

            val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
            return if (jsonFile.exists()) {
                val json = jsonFile.readText()
                GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                Basket(mutableListOf())
            }
        }

        const val BASKET_FILE = "basket.json"
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
}

class BasketItem(val meal: Meals,var quantity: Int ): Serializable{

}
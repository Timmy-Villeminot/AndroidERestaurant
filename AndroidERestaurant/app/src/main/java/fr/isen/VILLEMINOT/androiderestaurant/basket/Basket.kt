package fr.isen.VILLEMINOT.androiderestaurant.basket

import fr.isen.VILLEMINOT.androiderestaurant.network.Meals
import java.io.Serializable

class Basket(val items: MutableList<BasketItem>): Serializable {
    fun addItem(item: Meals, quantity: Int){

    }

    fun save(){

    }

    companion object{
        fun getBasket(): Basket{
            return Basket(mutableListOf())
        }
    }
}

class BasketItem(val meal: Meals,var quantity: Int ): Serializable{

}
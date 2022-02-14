package fr.isen.VILLEMINOT.androiderestaurant.basket

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.VILLEMINOT.androiderestaurant.R
import fr.isen.VILLEMINOT.androiderestaurant.databinding.CellBasketBinding
import fr.isen.VILLEMINOT.androiderestaurant.network.Meals

class BasketAdapter(private val items: List<BasketItem>,
                    val deleteClickListener: (BasketItem)-> Unit): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    lateinit var context: Context
    class BasketViewHolder(binding: CellBasketBinding): RecyclerView.ViewHolder(binding.root) {
        val mealName : TextView = binding.name
        val price : TextView = binding.price
        val quantity : TextView = binding.quantity
        val delete : ImageButton = binding.deleteButton
        val imageView : ImageView = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        context = parent.context
        return BasketViewHolder(CellBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }



    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItem = items[position]
        holder.mealName.text = basketItem.meal.name
        holder.quantity.text = "${context?.getString(R.string.quantity)} ${basketItem.quantity.toString()}"

        holder.price.text = "${basketItem.meal.prices.first().price} €"

        holder.delete.setOnClickListener{
            deleteClickListener.invoke(basketItem)
        }

        Picasso.get()
            .load(basketItem.meal.getThumbnailURL())
            .placeholder(R.drawable.android_restau_logo)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
package fr.isen.VILLEMINOT.androiderestaurant.Detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.VILLEMINOT.androiderestaurant.CategoryActivity
import fr.isen.VILLEMINOT.androiderestaurant.R
import fr.isen.VILLEMINOT.androiderestaurant.basket.Basket
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.VILLEMINOT.androiderestaurant.network.Meals
import kotlin.math.max

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var currentMeals: Meals? = null
    private var itemCount = 1F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentMeals = intent.getSerializableExtra(CategoryActivity.SELECTED_ITEM) as? Meals
        binding.title.text = currentMeals?.name
        setupContent()
        observeClick()
        refreshShopButton()
    }

    private fun setupContent(){
        binding.title.text = currentMeals?.name
        binding.ingredients.text = currentMeals?.ingredients?.map { it.name }?.joinToString(" | ")
        currentMeals?.let {
            binding.viewPager.adapter = PhotoAdapter(this, it.images)
        }

        //binding.viewPager.adapter
    }

    private fun refreshShopButton(){
        currentMeals?.let { meal ->
            val price: Float = meal.prices.first().price.toFloat()
            val total = price * itemCount
            binding.buttonShop.text = "${getString(R.string.total)} $total €"
            binding.quantity.text = itemCount.toInt().toString()
        }


    }

    private fun observeClick(){
        binding.icLess.setOnClickListener{
            itemCount= max(1f, itemCount-1)
            refreshShopButton()
            /*val count = itemCount -1
            if(count > 0){
                itemCount = count
                refreshShopButton()
            }*/

        }
        binding.icMore.setOnClickListener{
            itemCount++
            refreshShopButton()
        }
        binding.buttonShop.setOnClickListener {
            currentMeals?.let { meal ->
                val basket = Basket.getBasket()
                basket.addItem(meal, itemCount.toInt())
            }
        }
    }
}
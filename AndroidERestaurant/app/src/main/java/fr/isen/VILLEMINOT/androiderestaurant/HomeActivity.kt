package fr.isen.VILLEMINOT.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenClick()
    }

    private fun listenClick() {
        binding.buttonstarter.setOnClickListener {
            showCategory(LunchType.STARTER)
        }
        binding.buttonmeals.setOnClickListener {
            showCategory(LunchType.MEAL)
        }
        binding.buttondesserts.setOnClickListener {
            showCategory(LunchType.DESSERT)
        }
    }
    private fun showCategory(item: LunchType) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(HomeActivity.CategoryType, item)
        startActivity(intent)
    }

    companion object {
        const val CategoryType ="CategoryType"
    }
}
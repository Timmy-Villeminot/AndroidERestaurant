package fr.isen.VILLEMINOT.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenClick()

        Log.d("Life cycle", "HomeActivity onCreate")
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

    override fun onDestroy() {
        Log.d("life cycle", "HomeActivity onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life cycle", "HomeActivity onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life cycle", "HomeActivity onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life cycle", "HomeActivity onStart")
    }


    override fun onStop() {
        Log.d("life cycle", "HomeActivity onStop")
        super.onStop()
    }

    companion object {
        const val CategoryType ="CategoryType"
    }
}
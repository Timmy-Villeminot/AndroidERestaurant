package fr.isen.VILLEMINOT.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityCategoryActvityBinding

enum class LunchType {
    STARTER, MEAL, DESSERT;

    companion object {
        fun getResString(type: LunchType): Int {
            return when(type) {
                STARTER -> R.string.starters
                MEAL -> R.string.meals
                DESSERT -> R.string.desserts
            }
        }
    }
}

class CategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryActvityBinding
    lateinit var currentCategory: LunchType

    val fakeItems = listOf("Plat1", "Plat2", "Plat3", "Plat4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        setupTitle()
        Log.d("life cycle", "CategoryActivity onCreate")
    }

    override fun onDestroy() {
        Log.d("life cycle", "CategoryActivity onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life cycle", "CategoryActivity onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life cycle", "CategoryActivity onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life cycle", "CategoryActivity onStart")
    }


    override fun onStop() {
        Log.d("life cycle", "CategoryActivity onStop")
        super.onStop()
    }

    private fun setupTitle() {
        binding.Title.text = getString(LunchType.getResString(currentCategory))
    }
}
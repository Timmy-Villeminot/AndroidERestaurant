package fr.isen.VILLEMINOT.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityCategoryActvityBinding

enum class LunchType {
    STARTER, MEAL, DESSERT;
}

class CategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item: LunchType = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        binding.textView.setText("Contenu du get Extra")
    }
}
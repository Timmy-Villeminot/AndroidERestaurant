package fr.isen.VILLEMINOT.androiderestaurant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra(CategoryActivity.SELECTED_ITEM)
    }
}
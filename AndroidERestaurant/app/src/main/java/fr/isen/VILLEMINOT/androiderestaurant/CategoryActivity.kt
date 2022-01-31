package fr.isen.VILLEMINOT.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.VILLEMINOT.androiderestaurant.HomeActivity.Companion.CategoryType
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

    val fakeItems = listOf("item1", "item2", "item3", "item4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        setupTitle()

        setupList()
        Log.d("life cycle", "CategoryActivity onCreate")
    }

    private fun setupTitle() {
        binding.title.text = getString(LunchType.getResString(currentCategory))
    }

    private fun setupList(){

        binding.itemRecycleView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(fakeItems){

            showDetail(it)
        }
        binding.itemRecycleView.adapter = adapter


    }

    private fun showDetail(item: String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(CategoryActivity.SELECTED_ITEM, item)
        startActivity(intent)
    }

    companion object {
        const val  SELECTED_ITEM ="SELECTED_ITEM"
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


}
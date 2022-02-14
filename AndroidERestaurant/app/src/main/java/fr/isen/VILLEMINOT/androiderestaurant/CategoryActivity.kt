package fr.isen.VILLEMINOT.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.VILLEMINOT.androiderestaurant.Detail.DetailActivity
import fr.isen.VILLEMINOT.androiderestaurant.HomeActivity.Companion.CategoryType
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityCategoryActvityBinding
import fr.isen.VILLEMINOT.androiderestaurant.network.Meals
import fr.isen.VILLEMINOT.androiderestaurant.network.MenuResult
import fr.isen.VILLEMINOT.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

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

        fun getCategoryTitle(type: LunchType): String{
            //filtrer catégories
            return when(type) {
                STARTER -> "Entrées"
                MEAL -> "Plats"
                DESSERT -> "Desserts"
            }
        }
    }
}


class CategoryActivity : BaseActivity() {
    lateinit var binding: ActivityCategoryActvityBinding
    lateinit var currentCategory: LunchType

    //val fakeItems = listOf("item1", "item2", "item3", "item4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(CategoryType) as? LunchType
            ?: LunchType.STARTER
        setupTitle()
        makeRequest()

        Log.d("life cycle", "CategoryActivity onCreate")
    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL + NetworkConstants.MENU
        val parameters = JSONObject() //dico de type JSON donc put les élé
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            {
                //retour si bien passé
                parseResult(it.toString())
            },
            {
                //retour si erreur
                Log.d("Volley error", "$it")

            })
        queue.add(request)
    }

    private fun parseResult(response: String) {
        val result = GsonBuilder().create().fromJson(response, MenuResult::class.java)
        val items = result.data.firstOrNull {
            it.name == LunchType.getCategoryTitle(currentCategory)
        }?.items

        /*if (items != null) {
            setupList(items)
        }*/
        items?.let {
            setupList(it)
        }
    }

    private fun setupTitle() {
        binding.title.text = getString(LunchType.getResString(currentCategory))
    }

    private fun setupList(items: List<Meals>) {

        binding.itemRecycleView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(items) {

            showDetail(it)
        }
        binding.itemRecycleView.adapter = adapter


    }

    private fun showDetail(item: Meals) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SELECTED_ITEM, item)
        startActivity(intent)
    }

    companion object {
        const val SELECTED_ITEM = "SELECTED_ITEM"
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
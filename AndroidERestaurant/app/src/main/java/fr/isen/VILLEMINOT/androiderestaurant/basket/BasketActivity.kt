package fr.isen.VILLEMINOT.androiderestaurant.basket

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.VILLEMINOT.androiderestaurant.User.UserActivity
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.VILLEMINOT.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadList()

        binding.orderButton.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }


    private fun loadList() {
        val basket = Basket.getBasket(this)
        val items = basket.items
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadList()
        }
    }

    private fun makeRequest(){
        val path = NetworkConstants.BASE_URL + NetworkConstants.ORDER

        val queue = Volley.newRequestQueue(this)


        val basket = Basket.getBasket(this)

        val sharedPreferences = getSharedPreferences(UserActivity.USER_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val parameters = JSONObject()

        parameters.put(NetworkConstants.KEY_MSG,basket.toJson())
        parameters.put(NetworkConstants.KEY_USER, sharedPreferences.getInt(UserActivity.ID_USER, -1))
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)

        val request = JsonObjectRequest(
            Request.Method.POST,
            path,
            parameters,
            {
            Log.d("request", it.toString(2))
                basket.clear()
                basket.save(this)
                finish()
            },
            {
                Log.d("request", it.message ?: "Une erreur est survenue")
            }
        )

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "SEND ORDER", Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        const val REQUEST_CODE = 111
    }
}
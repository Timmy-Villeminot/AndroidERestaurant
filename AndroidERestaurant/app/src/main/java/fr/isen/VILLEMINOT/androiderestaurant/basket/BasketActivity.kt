package fr.isen.VILLEMINOT.androiderestaurant.basket

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.VILLEMINOT.androiderestaurant.User.UserActivity
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityBasketBinding

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
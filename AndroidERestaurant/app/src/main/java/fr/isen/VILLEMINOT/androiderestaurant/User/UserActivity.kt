package fr.isen.VILLEMINOT.androiderestaurant.User

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.google.gson.GsonBuilder
import fr.isen.VILLEMINOT.androiderestaurant.R
import fr.isen.VILLEMINOT.androiderestaurant.databinding.ActivityUserBinding
import fr.isen.VILLEMINOT.androiderestaurant.network.NetworkConstants
import fr.isen.VILLEMINOT.androiderestaurant.network.User
import fr.isen.VILLEMINOT.androiderestaurant.network.UserResult
import org.json.JSONObject


interface UserActivityFragmentInteration {
    fun showLogin()
    fun showRegister()
    fun makeRequest(email: String?, password : String?, firstName: String?, lastName: String?, isFromLogin: Boolean)
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteration {
    lateinit var binding : ActivityUserBinding
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, registerFragment).commit()
    }

    override fun showLogin() {
        //afficher fragment Login

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit()

    }

    override fun showRegister(){
        //ici register

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, registerFragment).commit()
    }

    override fun makeRequest(
        email: String?,
        password: String?,
        firstName: String?,
        lastName: String?,
        isFromLogin: Boolean
    ) {
        if (verifyInformation(email, password, firstName, lastName, isFromLogin)){
            //launchRequest
            launchRequest(email, password, firstName, lastName, isFromLogin)

        }else{
            //affiche message   champs invalide
            Toast.makeText(this, getString(R.string.invalidForms), Toast.LENGTH_LONG)?.show()
        }
    }

    private fun launchRequest(email: String?,
                              password: String?,
                              firstName: String?,
                              lastName: String?,
                              isFromLogin: Boolean) {
        val queue = Volley.newRequestQueue(this)
        var requestPath = NetworkConstants.BASE_URL
        if (isFromLogin){
            requestPath += NetworkConstants.LOGIN
        }else{
            requestPath += NetworkConstants.REGISTER
        }

        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        parameters.put(NetworkConstants.KEY_EMAIL, email)
        parameters.put(NetworkConstants.KEY_PASSWORD, password)
        if (!isFromLogin){
            parameters.put(NetworkConstants.KEY_FIRSTNAME, firstName)
            parameters.put(NetworkConstants.KEY_LASTNAME, lastName)
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            requestPath,
            parameters,
            {
                val userResult = GsonBuilder().create().fromJson(it.toString(), UserResult::class.java)
                if (userResult.data !=null) {
                    saveUser(userResult.data)
                }else{

                }
            },
            {
                Log.d("request", it.message ?: "Une erreur est survenue")
            }

        )
        queue.add((request))
    }


    private fun saveUser(user: User){
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ID_USER, user.id)
        editor.apply()

        setResult(Activity.RESULT_OK)
        finish()
    }

    fun verifyInformation(
        email: String?,
        password: String?,
        firstName: String?,
        lastName: String?,
        isFromLogin: Boolean
    ) : Boolean{
        var verified = (email?.isNotEmpty() == true && password?.count() ?: 0 >=6)

        if (!isFromLogin) {
            verified = verified && (firstName?.isNotEmpty() == true && lastName?.isNotEmpty() == true)
        }
        return verified
    }

    companion object {
        const val USER_PREFERENCES_NAME= "USER_PREFERENCES_NAME"
        const val ID_USER = "ID_USER"
    }
}
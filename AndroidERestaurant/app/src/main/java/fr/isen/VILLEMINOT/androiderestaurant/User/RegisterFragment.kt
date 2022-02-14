package fr.isen.VILLEMINOT.androiderestaurant.User

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isen.VILLEMINOT.androiderestaurant.R
import fr.isen.VILLEMINOT.androiderestaurant.databinding.FragmentRegisterBinding


class RegisterFragment() : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    var interactor : UserActivityFragmentInteration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        interactor = context as? UserActivityFragmentInteration
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener{
            interactor?.showLogin()

        }
        binding.createButton.setOnClickListener{
            interactor?.makeRequest(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.firstName.text.toString(),
                binding.lastName.text.toString(),
                false)
        }
    }
}
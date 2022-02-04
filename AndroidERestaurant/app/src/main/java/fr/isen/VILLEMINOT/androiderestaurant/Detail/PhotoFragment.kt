package fr.isen.VILLEMINOT.androiderestaurant.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.VILLEMINOT.androiderestaurant.R
import fr.isen.VILLEMINOT.androiderestaurant.databinding.FragmentPhotoBinding
import java.net.URL

class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(URL)
        if (url?.isNotEmpty() == true)
            Picasso.get().load(url).placeholder(R.drawable.android_restau_logo).into(binding.pic)
    }

    companion object{
        fun newInstance(url: String) = PhotoFragment().apply { arguments = Bundle().apply { putString(
            URL, url) } }
            const val URL = "URL"

    }

}
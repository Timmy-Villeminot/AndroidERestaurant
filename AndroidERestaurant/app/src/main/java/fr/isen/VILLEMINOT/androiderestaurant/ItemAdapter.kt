package fr.isen.VILLEMINOT.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.VILLEMINOT.androiderestaurant.databinding.CellMainBinding


class ItemAdapter(val items: List<String>, val itemClickListener: (String)-> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    // Création pont discussion entre entre activité et adapter
    //itemClickListener: Type qu'on renvoie = bloc (revoie rien)/ Besoin string
    class ItemViewHolder(binding: CellMainBinding): RecyclerView.ViewHolder(binding.root){
        //mapper contenu cellule
        val title: TextView = binding.mainCourse
        // Création viewModel

        //Doit récup root de la cellule
        val layout: ConstraintLayout = binding.root
    }

    override fun onCreateViewHolder(viewparent: ViewGroup, viewType: Int): ItemViewHolder {
        //créer view holder et attacher layout
        val binding = CellMainBinding.inflate(LayoutInflater.from(viewparent.context), viewparent, false)
        //.context : set à  récup le contexte  de l'activité
        // false : s'attache pas parent dans cellule
        return ItemViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        //appel au moment affichage cellule
        val item = items[position]
        viewHolder.title.text = item

        viewHolder.layout.setOnClickListener{
            itemClickListener.invoke(item) //Représente bloc de code
        }
    }

    override fun getItemCount(): Int {
        //informer recycle view  nombre item liste
        return items.count()
    }

}
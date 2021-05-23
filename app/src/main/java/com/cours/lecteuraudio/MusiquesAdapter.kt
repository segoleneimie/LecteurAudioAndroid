package com.cours.lecteuraudio

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cours.lecteuraudio.bdd.AppDatabaseHelper
import com.cours.lecteuraudio.bdd.MusiqueFavoriteDTO
import kotlinx.android.synthetic.main.item_musique.view.*
import com.cours.lecteuraudio.MainActivity

class MusiquesAdapter(private var listeMusique: MutableList<Musique>) :
RecyclerView.Adapter<MusiquesAdapter.MusiqueViewHolder>()
{


    // Crée chaque vue item à afficher :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusiqueViewHolder
    {
        val viewMusique = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_musique, parent, false)
        return MusiqueViewHolder(viewMusique)


    }

    // Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: MusiqueViewHolder, position: Int)
    {
        holder.textViewTitreMusique.text = listeMusique[position].titre
        holder.textViewTailleMusique.text = listeMusique[position].taille
        holder.textViewDureeMusique.text = listeMusique[position].duree
        holder.buttonSupprimeFav.isVisible = false
        holder.buttonEnregistreFav.context
    }

    override fun getItemCount(): Int = listeMusique.size
    // ViewHolder :
    inner class MusiqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewTitreMusique: TextView = itemView.findViewById(R.id.titre_musique)
        val textViewTailleMusique: TextView = itemView.findViewById(R.id.taille_musique)
        val textViewDureeMusique: TextView = itemView.findViewById(R.id.duree_musique)
        val buttonEnregistreFav: Button = itemView.findViewById(R.id.buttonEnregistreFav)
        val buttonSupprimeFav: Button = itemView.findViewById(R.id.buttonSupprimeFav)
        init {
            buttonEnregistreFav.setOnClickListener {
                val musique = listeMusique[adapterPosition]
                val musiqueFav = MusiqueFavoriteDTO()
                musiqueFav.titre = musique.titre
                musiqueFav.artiste = musique.artiste
                musiqueFav.taille = musique.taille
                musiqueFav.duree = musique.duree
                val context = buttonEnregistreFav.context
                AppDatabaseHelper.getDatabase(context as Context).musiquesFavoritesDAO().insert(musiqueFav)
                buttonSupprimeFav.isVisible = !buttonSupprimeFav.isVisible
                buttonEnregistreFav.isVisible = !buttonEnregistreFav.isVisible

                Log.d("FAVORIS", "enregistre favoris")
            }
            buttonSupprimeFav.setOnClickListener {
                val musique = listeMusique[adapterPosition]
                val musiqueFav = MusiqueFavoriteDTO()
                musiqueFav.titre = musique.titre
                musiqueFav.artiste = musique.artiste
                musiqueFav.taille = musique.taille
                musiqueFav.duree = musique.duree
                val context = buttonEnregistreFav.context
                AppDatabaseHelper.getDatabase(context).musiquesFavoritesDAO().delete(musiqueFav)
                buttonSupprimeFav.isVisible = !buttonSupprimeFav.isVisible
                buttonEnregistreFav.isVisible = !buttonEnregistreFav.isVisible
                Log.d("FAVORIS", "supprime favoris")
            }
        }


    }

}
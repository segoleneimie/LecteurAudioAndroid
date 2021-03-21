package com.cours.lecteuraudio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
    }

    override fun getItemCount(): Int = listeMusique.size
    // ViewHolder :
    inner class MusiqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewTitreMusique: TextView = itemView.findViewById(R.id.titre_musique)
        val textViewTailleMusique: TextView = itemView.findViewById(R.id.taille_musique)
        val textViewDureeMusique: TextView = itemView.findViewById(R.id.duree_musique)
    }
}
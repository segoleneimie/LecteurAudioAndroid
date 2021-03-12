package com.cours.lecteuraudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // à ajouter pour de meilleures performances :
        liste_musiques.setHasFixedSize(true)

        // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        liste_musiques.layoutManager = layoutManager
        // contenu d'exemple :
        val listeMusique: MutableList<Musique> = ArrayList()
        for (i in 0..50){
            listeMusique.add(Musique("Pommes", "30Mo", "3min"))
            listeMusique.add(Musique("Poires", "30Mo", "3min"))
        }

        // adapter :
        val musiquesAdapter = MusiquesAdapter(listeMusique)
        liste_musiques.adapter = musiquesAdapter
    }
}
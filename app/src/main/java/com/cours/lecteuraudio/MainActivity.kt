package com.cours.lecteuraudio

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        Log.d("monTag", "onCreate")
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Log.d("monTag", "permission granted 2")
            afficherMusics()
        }
        else
        {
// affichage de la popup de demande de permission :
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                123
            )
        }
//         Penser à informer l'utilisateur de la raison de la permission si elle est refusée.
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    )
    {
//        Log.d("PermissionsResult", "requestCode")
        if (requestCode == 123)
        {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Log.d("monTag", "permission granted 1")
                // permission accordée, on peut accéder aux contacts sans risque
                afficherMusics()
            }
            else
            {
                // permission refusée, on ne peut pas accéder aux contacts
                Toast.makeText(this, R.string.main_message_permission_refusee, Toast.LENGTH_LONG).show()
            }
        }
    }
    /**
     * Affichage des contacts.
     */
    private fun afficherMusics()
    {
        Log.d("afficherMusics", "called")
        // chargement :
        val musicsDAO = MusicsDAO()
        val listeMusics = musicsDAO.getListeMusics(this)

        // affichage de la liste de contacts :
        val stringBuilderMusics = StringBuilder()

        for (music in listeMusics)
        {

            music.title?.let { Log.d("music", it) }
            stringBuilderMusics.append(music.title).append(" : ").append(music.artist).append("\n")
        }

        texte_musics.text = stringBuilderMusics.toString()
    }


}
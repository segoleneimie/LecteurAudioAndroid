package com.cours.lecteuraudio

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.cours.lecteuraudio.bdd.AppDatabaseHelper
import com.cours.lecteuraudio.bdd.MusiqueFavoriteDTO
import com.cours.lecteuraudio.bdd.MusiquesFavoritesDAO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_musique.*


class MainActivity : AppCompatActivity() {

//    // Référence :
//    private var musicService: MusicService? = null
//
//    // Callback pour le binding, via un ServiceConnection :
//    private val connexion: ServiceConnection = object : ServiceConnection
//    {
//        override fun onServiceConnected(className: ComponentName, binder: IBinder)
//        {
//            musicService = (binder as MusicService.MonBinder).service
//        }
//        override fun onServiceDisconnected(className: ComponentName)
//        {
//            musicService = null
//        }
//
//
//    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("monTag", "onCreate")
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Log.d("monTag", "permission granted 2")
            afficherMusiques()
        }
        else
        {
// affichage de la pop up de demande de permission :
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
                afficherMusiques()
            }
            else
            {
                // permission refusée, on ne peut pas accéder aux contacts
                Toast.makeText(this, R.string.main_message_permission_refusee, Toast.LENGTH_LONG).show()
            }
        }
    }
    /**
     * Affichage de la liste des musiques.
     */
    private fun afficherMusiques()
    {
        Log.d("afficherMusics", "called")
        buttonListeFavoris.isVisible = false
        buttonGetListeFavoris.isVisible = true
        // chargement :
        val musiquesDAO = MusiquesDAO()
        val listeMusiques = musiquesDAO.getListeMusiques(this)

        // affichage de la liste de contacts :
//        val stringBuilderMusics = StringBuilder()
        // PEUT ETRE UTILE POUR FORMATER LA TAILLE OU LA DUREE

        // à ajouter pour de meilleures performances :
        liste_musiques.setHasFixedSize(true)

        // adapter :
        val musiquesAdapter = MusiquesAdapter(listeMusiques as MutableList<Musique>)
        liste_musiques.adapter = musiquesAdapter

        // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        liste_musiques.layoutManager = layoutManager
    }

//    fun playItem(view: View)
//    {
//        Log.d("playItem", "click item ok")
//        Log.i("tag", "nombre : ${musicService?.getNombre()}")
//
////        START MUSIC SERVICE
//        val intent = Intent(this, MusicService::class.java)
////        intent.putExtra()
//        bindService(intent, connexion, Context.BIND_AUTO_CREATE)
//        startService(intent)
//
//    }



//    override fun onStart()
//    {
//        super.onStart()
//        val intent = Intent(this, MusicService::class.java)
//        bindService(intent, connexion, Context.BIND_AUTO_CREATE)
//    }
//    override fun onStop()
//    {
//        super.onStop()
//        musicService?.run { unbindService(connexion) }
//    }

    /**
     * affiche les musiques favorites
     */
    fun getListeFavoris(view: View)
    {
        if (buttonListeFavoris.isVisible) {
            afficherMusiques()
        } else {

            liste_musiques.adapter = null
            Log.d("FAVORIS", "charge liste")
            // chargement :
            val musiquesFavoritesDAO = AppDatabaseHelper.getDatabase(this).musiquesFavoritesDAO()
            val listeMusiquesFavorites = musiquesFavoritesDAO.getListeMusiquesFavorites()
            Log.d("FAVORIS", listeMusiquesFavorites.toString())


            if (!listeMusiquesFavorites.isNullOrEmpty())
            {

                val favorisList = mutableListOf<Musique>()
                for (a in 0..listeMusiquesFavorites.lastIndex) {

                    listeMusiquesFavorites[a].titre?.let {
                        listeMusiquesFavorites[a].artiste?.let { it1 ->
                            Musique(
                                it,
                                it1,
                                listeMusiquesFavorites[a].taille,
                                listeMusiquesFavorites[a].duree
                            )
                        }
                    }?.let { favorisList.add(it) }
                    // insérer la liste des favoris dans la liste des musiques de l'adapter
                    val liste = favorisList?.let {
                        MusiquesAdapter(it.toMutableList())
                    }
                    liste_musiques.adapter = liste

                    // layout manager, décrivant comment les items sont disposés :
                    val layoutManager = LinearLayoutManager(this)
                    liste_musiques.layoutManager = layoutManager

                }
                buttonGetListeFavoris.isVisible = !buttonGetListeFavoris.isVisible
                buttonListeFavoris.isVisible = !buttonListeFavoris.isVisible

            } else {
                Toast.makeText(this,"il n'y a pas de favoris d'enregistrés",Toast.LENGTH_LONG).show()
            }
        }



    }




}
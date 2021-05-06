package com.cours.lecteuraudio

import android.Manifest
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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // Référence :
    private var musicService: MusicService? = null
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

    fun playItem(view: View)
    {
        Log.d("playItem", "click item ok")
        Log.i("tag", "nombre : ${musicService?.getNombre()}")
//        START MUSIC SERVICE
        val intent = Intent(this, MusicService::class.java)
        startService(intent)

    }

    // Callback pour le binding, via un ServiceConnection :
    private val connexion: ServiceConnection = object : ServiceConnection
    {
        override fun onServiceConnected(className: ComponentName, binder: IBinder)
        {
            musicService = (binder as MusicService.MonBinder).service
        }
        override fun onServiceDisconnected(className: ComponentName)
        {
            musicService = null
        }


    }

    override fun onStart()
    {
        super.onStart()
        val intent = Intent(this, MusicService::class.java)
        bindService(intent, connexion, Context.BIND_AUTO_CREATE)
    }
    override fun onStop()
    {
        super.onStop()
        musicService?.run { unbindService(connexion) }
    }


}
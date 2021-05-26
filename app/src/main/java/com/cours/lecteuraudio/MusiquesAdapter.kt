package com.cours.lecteuraudio

import android.content.Context
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.IBinder
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
    // Référence :
    private var musicService: MusicService? = null

    private var mediaPlayer: MediaPlayer? = null

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

//    fun onStart()
//    {
//        super.onStart()
//        val intent = Intent(this, MusicService::class.java)
//        bindService(intent, connexion, Context.BIND_AUTO_CREATE)
//    }
//    fun onStop()
//    {
//        super.onStop()
//        musicService?.run { unbindService(connexion) }
//    }


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
        holder.buttonSupprimeFav.isVisible = listeMusique[position].favoris
        holder.buttonEnregistreFav.isVisible = !listeMusique[position].favoris
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
                musiqueFav.uri = musique.uri
                musique.favoris = true
                val context = buttonEnregistreFav.context
                AppDatabaseHelper.getDatabase(context as Context).musiquesFavoritesDAO().insert(musiqueFav)
                buttonSupprimeFav.isVisible = musique.favoris
                buttonEnregistreFav.isVisible = !musique.favoris

                Log.d("FAVORIS", "enregistre favoris")
            }
            buttonSupprimeFav.setOnClickListener {
                val musique = listeMusique[adapterPosition]
                val musiqueFav = MusiqueFavoriteDTO()
                musiqueFav.titre = musique.titre
                musiqueFav.artiste = musique.artiste
                musiqueFav.taille = musique.taille
                musiqueFav.duree = musique.duree
                musiqueFav.uri = musique.uri
                musique.favoris = false
                val context = buttonSupprimeFav.context
                AppDatabaseHelper.getDatabase(context).musiquesFavoritesDAO().delete(musiqueFav)
                buttonSupprimeFav.isVisible = musique.favoris
                buttonEnregistreFav.isVisible = !musique.favoris

                Log.d("FAVORIS", "supprime favoris")
            }
            textViewTitreMusique.setOnClickListener {
                val musique = listeMusique[adapterPosition]
                Log.d("position", "$adapterPosition")
                musique.titre?.let { it1 -> Log.d("titre", it1) }
                Log.d("uri", "${musique.uri}")
                Log.d("playItem", "click item ok")
//        Log.i("tag", "nombre : ${musicService?.getNombre()}")
                val musiqueURI = musique.uri
//                textViewTitreMusique.setTypeface(textViewTitreMusique.typeface, Typeface.BOLD)

//        START MUSIC SERVICE
                val intent = Intent(itemView.context, MusicService::class.java)
                intent.putExtra("action", "PLAY")
                intent.putExtra("musiqueURI", "$musiqueURI")
                intent.putExtra("currentPhoneIndex", "$musiqueURI")
//                itemView.context.bindService(intent, connexion, itemView)
                itemView.context.startService(intent)
            }

        }

    }

}







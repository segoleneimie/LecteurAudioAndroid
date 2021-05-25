package com.cours.lecteuraudio

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log


class MusicService : Service(), MediaPlayer.OnPreparedListener {

    private var mediaPlayer: MediaPlayer? = null


    // Binder :
    private val binder: IBinder = MonBinder()
    // Classe MonBinder :
    inner class MonBinder : Binder()
    {
        val service: MusicService
            get() = this@MusicService
    }

    override fun onCreate()
    {
        super.onCreate()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Log.d("onStartCommand", "$intent.action")
        Log.d("musiqueURI", "$intent.musiqueURI")
        val action: String? = intent?.extras?.getString("action")
        val musiquePath: String? = intent?.extras?.getString("musiqueURI")

        when (action) {
            "PLAY" -> {
                Log.d("PLAY", "PLAY called")
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(this@MusicService, Uri.parse(musiquePath))
                    prepare()
                    start()
                }
            }

        }


        return super.onStartCommand(intent, flags, startId)
    }

    /** Called when MediaPlayer is ready */
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun onBind(intent: Intent): IBinder = binder

    // Méthode d'exemple exposée aux composants clients :
    fun getNombre() = Math.random()

    override fun onUnbind(intent: Intent?): Boolean
    {
        return super.onUnbind(intent)
    }
    override fun onDestroy()
    {
        super.onDestroy()
    }
}
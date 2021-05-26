package com.cours.lecteuraudio

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi


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
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Log.d("onStartCommand", "$intent.action")
        Log.d("musiqueURI", "$intent.musiqueURI")
        val action: String? = intent?.extras?.getString("action")
        val musiquePath: String? = intent?.extras?.getString("musiqueURI")

        when (action) {
            "PLAY" -> {
                Log.d("PLAY", "PLAY called")

                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer!!.stop()
                }
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(this@MusicService, Uri.parse(musiquePath))
                    setOnPreparedListener(this@MusicService)
                    prepareAsync() // prepare async to not block main thread
                }
            }
            "PAUSE" -> {
                mediaPlayer!!.pause()
            }
            "STOP" -> {
                mediaPlayer!!.stop()
            }

        }

        if (mediaPlayer!!.isPlaying) {

//            audio_one.setBackgroundColor(Color.RED)
//            audio_one.isClickable = false
//            audio_two.isClickable = false
        }

        mediaPlayer!!.setOnCompletionListener {
//            audio_one.setBackgroundColor(Color.GRAY)
//            audio_one.isClickable = true
//            audio_two.isClickable = true
            Log.d("setOnCompletionListener", "setOnCompletionListener called")

            it.release()
            it.reset()
//            goNextAudio()

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
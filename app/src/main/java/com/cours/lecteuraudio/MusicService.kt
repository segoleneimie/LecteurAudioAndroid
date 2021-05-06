package com.cours.lecteuraudio

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi

class MusicService : Service() {

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
        Log.d("onStartCommand", "$intent")
        return super.onStartCommand(intent, flags, startId)
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
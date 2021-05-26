package com.cours.lecteuraudio

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

class MusiquesDAO {

    fun getListeMusiques(context: Context): List<Musique>
    {
                //retrieve song info
        val musicResolver = context.contentResolver
        val musicUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val musicCursor: Cursor? = musicResolver.query(musicUri, null, null, null, null)


        val listeMusics: MutableList<Musique> = ArrayList()
        if (musicCursor != null)
        {
            try
            {
                while (musicCursor.moveToNext())
                {
                    // conversion des données remontées en un objet métier :
                    listeMusics.add(Musique(
                        musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                        musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.SIZE)),
                        musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION)),
                        musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA))
                    ))
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
            finally
            {
                musicCursor.close()
            }
        }
        return listeMusics
    }}

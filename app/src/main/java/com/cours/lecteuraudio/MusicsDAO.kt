package com.cours.lecteuraudio

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class MusicsDAO {
    fun getListeMusics(context: Context): List<Music>
    {
                //retrieve song info
        val musicResolver = context.contentResolver
        val musicUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicCursor: Cursor? = musicResolver.query(musicUri, null, null, null, null)

        val listeMusics: MutableList<Music> = ArrayList()
        if (musicCursor != null)
        {
            try
            {
                while (musicCursor.moveToNext())
                {
                    // conversion des données remontées en un objet métier :
                    listeMusics.add(Music(
                            musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                            musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))))
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





//        if (musicCursor != null && musicCursor.moveToFirst()) {


//        }
////            //get columns
////            do {
//                val titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
//                Log.d("title", musicCursor.getString(titleColumn))
////            }  while (musicCursor.moveToNext())
//
//            val idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)
//            val artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
////            //add songs to list
//            do {
////                val thisId = musicCursor.getLong(idColumn)
////                val thisTitle = musicCursor.getString(titleColumn)
////                val thisArtist = musicCursor.getString(artistColumn)
////                val musicsDAOList: List<Music> = emptyList()
////                musicsDAOList.add(Music(thisId, thisTitle, thisArtist))
//            } while (musicCursor.moveToNext())
//        }
//        return emptyList()
    }}

//        //retrieve song info
//        val musicResolver = contentResolver
//        val musicUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//        val musicCursor: Cursor? = musicResolver.query(musicUri, null, null, null, null)
//
//        if (musicCursor != null && musicCursor.moveToFirst()) {
//            //get columns
//            val titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
//            val idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)
//            val artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
//            //add songs to list
//            do {
//                val thisId = musicCursor.getLong(idColumn)
//                val thisTitle = musicCursor.getString(titleColumn)
//                val thisArtist = musicCursor.getString(artistColumn)
//                val musicList
//                musicList.add(Song(thisId, thisTitle, thisArtist))
//            } while (musicCursor.moveToNext())
//        }
//    }
















// projection (colonnes utilisées après la requête) :
//        val projection = arrayOf(
//            MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//            MusicsContract.CommonDataKinds.Phone.NUMBER)
// filtre (clause WHERE) :
//        val selection = MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ? "
//        val selectionArgs = arrayOf("John")
// tri :
//        val tri = MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC "
// requête :
//        val cursor = context.contentResolver.query(
//            MusicsContract.CommonDataKinds.Phone.CONTENT_URI, // table requétée
//            projection, // colonnes à retourner
//            selection, // colonnes WHERE
//            selectionArgs, // valeurs WHERE
//            tri) // ordre de tri

//        val listeMusics: MutableList<Music> = ArrayList()
//        if (cursor != null)
//        {
//            try
//            {
//                while (cursor.moveToNext())
//                {
//// conversion des données remontées en un objet métier :
//                    listeMusics.add(Music(
//                        cursor.getString(cursor.getColumnIndex(
//                            MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
//                        cursor.getString(cursor.getColumnIndex(
//                            MusicsContract.CommonDataKinds.Phone.NUMBER))))
//                }
//            }
//            catch (e: Exception) { e.printStackTrace() }
//            finally { cursor.close() }
//        }
//        return listeMusics

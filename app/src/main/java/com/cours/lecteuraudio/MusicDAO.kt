package com.cours.lecteuraudio

import android.content.Context

class MusicDAO {
//    fun getListeMusics(context: Context): List<Music>
//    {
//// projection (colonnes utilisées après la requête) :
//        val projection = arrayOf(
//            MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//            MusicsContract.CommonDataKinds.Phone.NUMBER)
//// filtre (clause WHERE) :
//        val selection = MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ? "
//        val selectionArgs = arrayOf("John")
//// tri :
//        val tri = MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC "
//// requête :
//        val cursor = context.contentResolver.query(
//            MusicsContract.CommonDataKinds.Phone.CONTENT_URI, // table requétée
//            projection, // colonnes à retourner
//            selection, // colonnes WHERE
//            selectionArgs, // valeurs WHERE
//            tri) // ordre de tri
//
//        val listeContacts: MutableList<Music> = ArrayList()
//        if (cursor != null)
//        {
//            try
//            {
//                while (cursor.moveToNext())
//                {
//// conversion des données remontées en un objet métier :
//                    listeContacts.add(Music(
//                        cursor.getString(cursor.getColumnIndex(
//                            MusicsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
//                        cursor.getString(cursor.getColumnIndex(
//                            MusicsContract.CommonDataKinds.Phone.NUMBER))))
//                }
//            }
//            catch (e: Exception) { e.printStackTrace() }
//            finally { cursor.close() }
//        }
//        return listeContacts
//    }
}
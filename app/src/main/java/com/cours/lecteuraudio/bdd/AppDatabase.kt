package com.cours.lecteuraudio.bdd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MusiqueFavoriteDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun musiquesFavoritesDAO(): MusiquesFavoritesDAO
}
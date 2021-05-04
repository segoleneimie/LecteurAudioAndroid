package com.cours.lecteuraudio.bdd

import androidx.room.*

@Dao
abstract class MusiquesDAO {
    @Query("SELECT * FROM musiquesFavorites")
    abstract fun getListeMusiquesFavorites(): List<MusiqueFavoriteDTO>
    @Insert
    abstract fun insert(vararg courses: MusiqueFavoriteDTO)
    @Delete
    abstract fun delete(vararg courses: MusiqueFavoriteDTO)
}
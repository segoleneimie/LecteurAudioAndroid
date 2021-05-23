package com.cours.lecteuraudio.bdd

import androidx.room.*

@Dao
abstract class MusiquesFavoritesDAO {
    @Query("SELECT * FROM musiquesFavorites")
    abstract fun getListeMusiquesFavorites(): List<MusiqueFavoriteDTO>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg musique: MusiqueFavoriteDTO)
    @Delete
    abstract fun delete(vararg musique: MusiqueFavoriteDTO)
}
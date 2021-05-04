package com.cours.lecteuraudio.bdd

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "musiquesFavorites")
class MusiqueFavoriteDTO(
    @PrimaryKey(autoGenerate = true)
    val musiqueId: Long = 0,
    val titre: String? = null,
    val taille: String? = null,
    val duree: String? = null)
{

}

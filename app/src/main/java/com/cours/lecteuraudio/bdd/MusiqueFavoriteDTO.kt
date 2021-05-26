package com.cours.lecteuraudio.bdd

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "musiquesFavorites")
class MusiqueFavoriteDTO(

    @PrimaryKey(autoGenerate = false)
    var uri: String = "0",
    var titre: String? = null,
    var artiste: String? = null,
    var taille: String? = null,
    var duree: String? = null,
     )
{

}

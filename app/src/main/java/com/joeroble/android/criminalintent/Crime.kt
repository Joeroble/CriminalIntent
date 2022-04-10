package com.joeroble.android.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// Creates the data class Crime which will have a unique ID for each one created, plus the
// data for the title as a string, a date and a boolean isSolved.
//Now an entity as well as setting the UUID to be the primary key in the database.

@Entity
data class Crime (@PrimaryKey val id: UUID = UUID.randomUUID(),
                  var title: String="",
                  var date: Date=Date(),
                  var isSolved: Boolean = false,
                  var suspect: String = "")
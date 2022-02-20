package com.joeroble.android.criminalintent

import java.util.*

// Creates the data class Crime which will have a unique ID for each one created, plus the
// data for the title as a string, a date and a boolean isSolved.
data class Crime (val id: UUID = UUID.randomUUID(), var title: String="", var date: Date=Date(),
                    var isSolved: Boolean = false)
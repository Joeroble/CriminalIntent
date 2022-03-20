package com.joeroble.android.criminalintent.database

import androidx.room.TypeConverter
import java.util.*

// sets up the type converters that will convert the data pulled from or to the database into their respective types
// such as converting the date from the long number to the date format, or converting to the date
// to milliseconds in long form as the database cannot handle date objects.
class CrimeTypeConverters {

    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date?{
        return millisSinceEpoch?.let{
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }

}
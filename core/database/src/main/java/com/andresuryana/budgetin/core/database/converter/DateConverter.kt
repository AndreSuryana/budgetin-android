package com.andresuryana.budgetin.core.database.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"

class DateConverter {

    @TypeConverter
    fun stringToDate(dateString: String?): Date? = dateString?.let {
        SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(it)
    }

    @TypeConverter
    fun dateToString(date: Date?): String? = date?.let {
        SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(it)
    }
}
package com.andresuryana.budgetin.feature.notification.util

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATE_FORMAT = "dd MMM yyyy"

object Ext {

    fun Date.formatDate(pattern: String = DATE_FORMAT) =
        try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.format(this)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
}
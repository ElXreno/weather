package com.elxreno.weather

import java.text.DateFormat
import java.util.*

class Utils {
    fun getDateFromTimestamp(timeStamp: Long): String {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
            .format(Date(timeStamp * 1000L))
    }
}
package com.example.util

import java.text.SimpleDateFormat
import java.util.*

fun dateTimeStringToTimeMil(format : SimpleDateFormat = dateTimeFormat, timeDate : String) : Long{
    return format.parse(timeDate).time
}

fun timeMilToDateString(format: SimpleDateFormat = dateTimeFormat , timMil : Long) : String{
    return format.format(Date(timMil))
}

val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.KOREA)
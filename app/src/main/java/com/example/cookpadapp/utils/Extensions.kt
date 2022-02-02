package com.example.cookpadapp.com.example.cookpadapp.utils

import java.text.SimpleDateFormat
import java.util.*


fun String.parseDate(): Date? {
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    return df.parse(this)
}

fun Date.userFormat(): String {
    val df = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    return df.format(this)
}
package com.reynaldiwijaya.bravo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.reynaldiwijaya.bravo.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImageUrl(context: Context, url : String) {
    Picasso.get()
        .load(url)
        .placeholder(createCircularProgressDrawable(context))
        .error(R.drawable.ic_broken_image)
        .into(this)
}

fun createCircularProgressDrawable(context: Context) : CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 4f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun emptyString() = ""

fun emptyData() = "Empty Data"

fun emptyScoreMatch() = "-"

@SuppressLint("SimpleDateFormat")
fun strToDate(strDate : String?, pattern : String = "yyyy-MM-dd") : Date? {
    val format = SimpleDateFormat(pattern)
    val date = strDate?.let { format.parse(it) }

    return date
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date : String, time : String) : Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"

    return formatter.parse(dateTime)
}

@SuppressLint("SimpleDateFormat")
fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy", Locale.ENGLISH).format(this)
}


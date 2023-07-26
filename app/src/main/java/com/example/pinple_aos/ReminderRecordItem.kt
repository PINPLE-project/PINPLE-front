package com.example.pinple_aos

import android.media.Image

data class ReminderRecordItem(
    val title: String,
    val dateTime: String,
    val status: String,
    val statusIMG: Int,
    val backgroundColor: Int,
    val textColor: Int)

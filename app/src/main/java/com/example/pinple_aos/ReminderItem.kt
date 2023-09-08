package com.example.pinple_aos

import com.example.pinple_aos.Retrofit2.NotificationItem

data class ReminderItem(val AREA_NM: String, val time: String)

fun NotificationItem.toReminderItem(): ReminderItem {
    return ReminderItem(AREA_NM, time)
}

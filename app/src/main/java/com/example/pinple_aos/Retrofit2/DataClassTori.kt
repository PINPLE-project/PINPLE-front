package com.example.pinple_aos.Retrofit2

import com.google.gson.annotations.SerializedName

data class ReminderResponse(
    @SerializedName("result") val result: List<NotificationItem>,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class NotificationItem(
    @SerializedName("AREA_NM") val AREA_NM: String,
    @SerializedName("time") val time: String
)
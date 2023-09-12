package com.example.pinple_aos.Retrofit2

import com.google.gson.annotations.SerializedName
import com.kakao.sdk.user.model.User

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

data class UserCreateRequest(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("charac") val charac: Int,
    @SerializedName("congestionAlarm") val congestionAlarm: Int,
    @SerializedName("pinAlarm") val pinAlarm: Int,
    @SerializedName("deviceToken") val deviceToken: String
)

data class UserCreateResponse(
    @SerializedName("result") val result: UserInfo,
    @SerializedName("inSuccess") val inSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class UserInfo(
    @SerializedName("userId") val userId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("character") val character: Int,
    @SerializedName("email") val email: String,
    @SerializedName("socialType") val socialType: Int
)


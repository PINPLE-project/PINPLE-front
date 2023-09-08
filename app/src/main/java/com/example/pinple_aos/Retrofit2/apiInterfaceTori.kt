package com.example.pinple_aos.Retrofit2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface apiInterfaceTori {

    // 설정된 알림 조회
    @GET ("/app/alert/setup")
    fun getReminder(
        @Header("userId") userId: String,
    ): Call<ReminderResponse>

}
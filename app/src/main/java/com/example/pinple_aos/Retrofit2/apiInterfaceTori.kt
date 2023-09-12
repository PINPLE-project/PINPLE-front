package com.example.pinple_aos.Retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface apiInterfaceTori {

    // 설정된 알림 조회
    @GET ("/app/alert/setup")
    fun getReminder(
        @Header("userId") userId: String,
    ): Call<ReminderResponse>

    // 유저 생성 (회원가입)
    @POST ("/app/users")
    fun createUser(
        @Body request: UserCreateRequest
    ): Call<UserCreateResponse>
}
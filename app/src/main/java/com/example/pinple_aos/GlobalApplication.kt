package com.example.pinple_aos

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    var kakaolgoin: Int? = null
    var googlelogin: Int? = null
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "a85a328d0b6714f24de2203795161478")
    }
}
package com.example.pinple_aos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // 카카오 SDK 초기화
        KakaoSdk.init(this, "a85a328d0b6714f24de2203795161478")

        // 자동로그인 처리
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        // 카카오 자동로그인 체크
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null || tokenInfo == null) {
                // 토큰 정보를 가져오는 데 실패하거나 토큰 정보가 없으면 로그인 화면으로 이동
                moveToAccess()
            } else {
                // 토큰 정보가 있는 경우, 토큰의 유효성을 확인
                val expiresIn = tokenInfo.expiresIn
                if (expiresIn > 0) {
                    // 토큰이 만료되지 않았으면 메인 화면으로 이동
                    moveToMain()
                } else {
                    // 토큰이 만료되었으면 로그인 화면으로 이동
                    moveToLogin()
                }
            }
        }
    }

    private fun moveToMain() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToAccess() {
        val intent = Intent(applicationContext, AccessRequestActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun moveToLogin() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

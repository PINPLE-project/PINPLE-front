package com.example.pinple_aos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // 카카오 SDK 초기화
        KakaoSdk.init(this, "a85a328d0b6714f24de2203795161478")

        // 구글 로그인 설정 초기화
        initGoogleSignIn()

        // 자동로그인 처리
        checkAutoLogin()
    }

    private fun initGoogleSignIn() {
        // Google Sign-In 설정 구성
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(this, gso)
    }

    private fun checkAutoLogin() {
        // 카카오 자동 로그인 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null || tokenInfo == null) {
                // 토큰 정보를 가져오는 데 실패하거나 토큰 정보가 없으면 구글 자동 로그인 확인
                checkGoogleAutoLogin()
            } else {
                // 토큰 정보가 있는 경우, 토큰의 유효성을 확인하여 메인 화면으로 이동
                val expiresIn = tokenInfo.expiresIn
                if (expiresIn > 0) {
                    moveToMain()
                } else {
                    moveToLogin()
                }
            }
        }
    }

    private fun checkGoogleAutoLogin() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            // 구글 로그인 성공한 경우 메인 화면으로 이동
            moveToMain()
        } else {
            // 구글 로그인 실패한 경우 로그인 화면으로 이동
            val intent = Intent(applicationContext, AccessRequestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun moveToMain() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToLogin() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

package com.example.pinple_aos

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "a85a328d0b6714f24de2203795161478")
    }
}

class LoginActivity : AppCompatActivity() {

    private fun moveToAnotherPage() {
        val intent = Intent(this, SettingNameActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            // 카카오톡 로그인 실패 또는 카카오톡 미설치
            UserApiClient.instance.loginWithKakaoAccount(this, callback = kakaoAccountLoginCallback)
        } else if (token != null) {
            // 카카오톡 로그인 성공
            // 추가 작업 수행
            moveToAnotherPage()
        }
    }
    private val kakaoAccountLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            // 카카오 계정 로그인 실패
            Toast.makeText(this, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            // 카카오 계정 로그인 성공
            // 추가 작업 수행
            moveToAnotherPage()
        }
    }

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            handleGoogleSignInResult(data)
        } else {
            // 구글 로그인 실패
            Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val kakaoLoginButton: ImageButton = findViewById(R.id.kakao_login_button)
        kakaoLoginButton.setOnClickListener {
            UserApiClient.instance.loginWithKakaoTalk(this, callback = kakaoLoginCallback)
        }

        val googleLoginButton: ImageButton = findViewById(R.id.google_login_button)
        googleLoginButton.setOnClickListener {
            signInWithGoogle()
        }

    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun handleGoogleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            // 구글 로그인 성공
            // 추가 작업 수행
            moveToAnotherPage()
        } catch (e: ApiException) {
            // 구글 로그인 실패
            Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_SHORT).show()
        }




    }
}
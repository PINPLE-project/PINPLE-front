package com.example.pinple_aos

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kakao.sdk.user.UserApiClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlin.math.log

class PreferenceActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        // 구글 로그인 설정 초기화
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // application에서 값 가져오기
        val app = application as GlobalApplication
        val kakaologinKey = app.kakaolgoin
        val googleloginKey = app.googlelogin

        // 로그인 api에 따라서 텍스트 변경
        val loginTxt = findViewById<TextView>(R.id.google_kakao_login_inf)

        if (kakaologinKey == 1) loginTxt.text = "(카카오로 로그인)"
        else if (googleloginKey == 1) loginTxt.text = "(구글로 로그인)"


        //api에서 계정 이메일 가져오기(구현중)
        val emailTxt = findViewById<TextView>(R.id.email_inf)

        if (kakaologinKey == 1) emailTxt.text = "kakao1234@gmail.com"
        else if (googleloginKey == 1) emailTxt.text = "google1234@gmail.com"



        //로그아웃 부분
        val logoutButton = findViewById<Button>(R.id.logout_btn)
        logoutButton.setOnClickListener {
            if (kakaologinKey == 1) {
                // 카카오 로그아웃 실행
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                    }
                    else {
                        val app = application as GlobalApplication
                        app.kakaolgoin = 0
                        moveToMainActivity()
                    }
                }
            }
            if (googleloginKey == 1) {
                // 구글 로그아웃 실행
                googleSignInClient.signOut().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // 구글 로그아웃 성공 처리
                        val app = application as GlobalApplication
                        app.googlelogin = 0
                        moveToMainActivity()
                    } else {
                        // 구글 로그아웃 실패 처리
                    }
                }
            }
        }

        //회원탈퇴 부분
        val withdrawlButton = findViewById<Button>(R.id.Withdrawal_btn)

        withdrawlButton.setOnClickListener {
            if (kakaologinKey == 1) {
                // 카카오 회원탈퇴 실행
                UserApiClient.instance.unlink { error ->
                    if (error != null) {
                    }
                    else {
                        val app = application as GlobalApplication
                        app.kakaolgoin = 0
                        moveToMainActivity()
                    }
                }
            }
            if (googleloginKey == 1) {
                // 구글 회원탈퇴 실행
                googleSignInClient.revokeAccess().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // 구글 회원탈퇴 성공 처리
                        val app = application as GlobalApplication
                        app.googlelogin = 0
                        moveToMainActivity()
                    } else {
                        // 구글 회원탈퇴 실패 처리
                    }
                }
            }

        }


    }

    private fun moveToMainActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }
}

package com.example.pinple_aos

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import com.example.pinple_aos.Retrofit2.RetrofitClient
import com.example.pinple_aos.Retrofit2.UserCreateRequest
import com.example.pinple_aos.Retrofit2.UserCreateResponse
import com.example.pinple_aos.Retrofit2.apiInterfaceTori
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.ContentValues.TAG
class ProfileActivity : AppCompatActivity() {

    private lateinit var blueImageView: ImageView
    private lateinit var blueTalkView: ImageView
    private lateinit var orangeImageView: ImageView
    private lateinit var orangeTalkView: ImageView
    private lateinit var activeButton: ImageButton
    private lateinit var nActivtbutton: ImageButton

    private var isBlueCharacterSelected: Boolean = false
    private var isOrangeCharacterSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // 닉네임 정보를 인텐트에서 가져오기
        val nickname = intent.getStringExtra("nickname")!!

        activeButton = findViewById(R.id.button_check_activate_)
        nActivtbutton = findViewById(R.id.button_check_disabled)
        blueImageView = findViewById(R.id.blue_char_image)
        blueTalkView = findViewById(R.id.blue_talk_img)
        orangeImageView = findViewById(R.id.orange_char_img)
        orangeTalkView = findViewById(R.id.orange_talk_img)

        val slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        val slideDownAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        val slideUpOrangeAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_orange)
        val slideDownOrangeAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_orange)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (isBlueCharacterSelected) {
                    blueImageView.setImageResource(R.drawable.blue_char_black)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        slideDownAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (!isBlueCharacterSelected) {
                    blueImageView.setImageResource(R.drawable.blue_char_white)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        slideUpOrangeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (isOrangeCharacterSelected) {
                    orangeImageView.setImageResource(R.drawable.orange_char_black)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        slideDownOrangeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (!isOrangeCharacterSelected) {
                    orangeImageView.setImageResource(R.drawable.orange_char_white)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        blueImageView.setOnClickListener {
            if (!isBlueCharacterSelected) {
                blueImageView.startAnimation(slideUpAnimation)
                blueTalkView.startAnimation(slideUpAnimation)
                activeButton.visibility = VISIBLE
                nActivtbutton.visibility = INVISIBLE

                if (isOrangeCharacterSelected) {
                    orangeImageView.startAnimation(slideDownOrangeAnimation)
                    orangeTalkView.startAnimation(slideDownOrangeAnimation)
                    activeButton.visibility = VISIBLE
                    nActivtbutton.visibility = INVISIBLE
                    isOrangeCharacterSelected = false
                }

                isBlueCharacterSelected = true
            }
        }

        orangeImageView.setOnClickListener {
            if (!isOrangeCharacterSelected) {
                orangeImageView.startAnimation(slideUpOrangeAnimation)
                orangeTalkView.startAnimation(slideUpOrangeAnimation)
                activeButton.visibility = VISIBLE
                nActivtbutton.visibility = INVISIBLE

                if (isBlueCharacterSelected) {
                    blueImageView.startAnimation(slideDownAnimation)
                    blueTalkView.startAnimation(slideDownAnimation)
                    activeButton.visibility = VISIBLE
                    nActivtbutton.visibility = INVISIBLE
                    isBlueCharacterSelected = false
                }

                isOrangeCharacterSelected = true
            }
        }

        activeButton.setOnClickListener {
            // 아래는 서버 배포 후 삭제될 부분
             val intent = Intent(this, MainActivity::class.java)
             intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
             startActivity(intent)
            //

            if (isBlueCharacterSelected || isOrangeCharacterSelected) {
                val selectedCharacter = if (isBlueCharacterSelected) 1 else 2

                // Retrofit API 서비스 객체 생성
                val apiService = RetrofitClient.getClient()?.create(apiInterfaceTori::class.java)

                // API 요청을 위한 데이터 객체 생성
                val userCreateRequest = UserCreateRequest(
                    nickname = nickname, // 닉네임을 사용하여 API 호출
                    charac = selectedCharacter,
                    congestionAlarm = 1,
                    pinAlarm = 1,
                    deviceToken = "f77sNkhkRtCGXl-xL8XGVr:APA91bE2uL5edqsDwyLOzzIcLmHk9_zQKsZlM1D_L7ubRcRIbEPuzo8yC0TKlZA0Ryj57CD0aqzLHIUwDfpbIEZgFiAmuMoa7GQDOnSmrwtvOYHxXZnxvEdXPcXaJlu-g-E4RX-q6Aw4"
                )

                // API 호출
                apiService?.createUser(userCreateRequest)?.enqueue(object : Callback<UserCreateResponse> {
                    override fun onResponse(call: Call<UserCreateResponse>, response: Response<UserCreateResponse>) {
                        if (response.isSuccessful) {
                            val userCreateResponse = response.body()
                            if (userCreateResponse?.inSuccess == true) {
                                // 회원가입 성공 처리
                                // val intent = Intent(this@ProfileActivity, MainActivity::class.java)
                                // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                // startActivity(intent)

                                Log.d(TAG, "API 호출 성공")
                            } else {
                                val errorMessage = "회원가입 실패 - 서버 응답 오류"
                                Log.e(TAG, errorMessage)
                            }
                        } else {
                            val errorMessage = "API 호출 실패 - HTTP 오류: ${response.code()}"
                            Log.e(TAG, errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<UserCreateResponse>, t: Throwable) {
                        val errorMessage = "네트워크 오류: ${t.message}"
                        Log.e(TAG, errorMessage)
                    }
                })
            } else {
                // 사용자가 캐릭터를 선택하지 않은 경우 처리
                // Handle case where user has not selected a character
            }
        }
    }
}

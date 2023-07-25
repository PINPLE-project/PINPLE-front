package com.example.pinple_aos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView

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
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }
}

package com.example.pinple_aos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.pinple_aos.databinding.ActivityLandingBinding
import com.kakao.sdk.common.util.Utility


class LandingActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewpagerFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(0, true)
        binding.dotsIndicator.attachTo(binding.viewPager)

        val moveButton = findViewById<ImageButton>(R.id.button_move_login)

        fun moveToAnotherPage(){
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        moveButton.setOnClickListener{
            moveToAnotherPage()
        }
    }
}



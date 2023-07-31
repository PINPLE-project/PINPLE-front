package com.example.pinple_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.annotation.RequiresApi


class AccessRequestActivity :  AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_request)

        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG
        )

        val requestCode = 1 // 권한 요청 코드 (임의로 지정 가능)

        val deniedPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (deniedPermissions.isNotEmpty()) {
            requestPermissions(deniedPermissions, requestCode)
        } else {
            // 이미 모든 권한이 허용된 상태라면 추가 작업 수행
        }

        val moveButton = findViewById<ImageButton>(R.id.button_check)

        fun moveToAnotherPage(){
            val intent = Intent(this, PreferenceActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        moveButton.setOnClickListener{
            moveToAnotherPage()
        }


    }
}
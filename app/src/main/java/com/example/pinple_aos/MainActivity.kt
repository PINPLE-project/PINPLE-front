package com.example.pinple_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.pinple_aos.R
import com.example.pinple_aos.fragments.MainFragment
import com.example.pinple_aos.fragments.MyPageFragment
import com.example.pinple_aos.fragments.PlaceFragment
import com.example.pinple_aos.fragments.ReminderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import android.util.Log
import androidx.core.content.ContextCompat
import android.graphics.drawable.Drawable

class MainActivity : AppCompatActivity() {

    private val mainFragment = MainFragment()
    private val placeFragment = PlaceFragment()
    private val reminderFragment = ReminderFragment()
    private val myPageFragment = MyPageFragment()

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // MainFragment로 시작
        replaceFragment(mainFragment)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            // 이전에 선택되었던 아이템의 아이콘 색상 원래 색상으로 되돌리기
            resetMenuItemColors()

            // 선택된 아이콘의 색상 변경
            menuItem.icon?.setTint(ContextCompat.getColor(this, R.color.color_main_black))

            when (menuItem.itemId) {
                R.id.tapmain -> {
                    replaceFragment(mainFragment)
                }
                R.id.tapplace -> {
                    replaceFragment(placeFragment)
                }
                R.id.tapreminder -> {
                    replaceFragment(reminderFragment)
                }
                R.id.tapmypage -> {
                    replaceFragment(myPageFragment)
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        Log.d("MainActivity", "$fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentCotainer, fragment)
            .commit()
    }

    private fun resetMenuItemColors() {
        val menu = bottomNavigationView.menu
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            menuItem.icon?.setTint(ContextCompat.getColor(this, R.color.color_gray_light))
        }
    }
}

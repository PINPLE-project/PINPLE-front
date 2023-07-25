package com.example.pinple_aos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class SettingNameActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var characterCountTextView: TextView
    private lateinit var firstButton: ImageButton
    private lateinit var secondButton: ImageButton
    private val maxLength = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_name)

        editText = findViewById(R.id.NickName)
        characterCountTextView = findViewById(R.id.text_counter)
        firstButton = findViewById(R.id.button_check_activate)
        secondButton = findViewById(R.id.button_check_disabled)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onNicknameTextChanged(s)
            }

            override fun afterTextChanged(s: Editable?) {
                val textLength = s?.length ?: 0
                val ratioText = "$textLength / $maxLength"
                characterCountTextView.text = ratioText
            }
        })

        firstButton.setOnClickListener {
            moveToAnotherPage()
        }
    }

    private fun onNicknameTextChanged(s: CharSequence?) {
        val nickname = s?.toString() ?: ""

        if (nickname.isNotEmpty()) {
            firstButton.visibility = View.VISIBLE
            secondButton.visibility = View.INVISIBLE
        } else {
            firstButton.visibility = View.INVISIBLE
            secondButton.visibility = View.VISIBLE
        }
    }

    private fun moveToAnotherPage() {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}


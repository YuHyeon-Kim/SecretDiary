package com.blueland.secretdiary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.blueland.secretdiary.databinding.ActivityDiaryBinding

class DiaryActivity : AppCompatActivity() {

    private val binding: ActivityDiaryBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_diary) }

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDiaryEdit()
    }

    private fun initDiaryEdit() {
        binding.apply {

            val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

            editText.setText(detailPreferences.getString("detail", ""))

            val runnable = Runnable {
                getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                    putString("detail", editText.text.toString())
                }
            }

            editText.addTextChangedListener {

                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 500)
            }
        }
    }
}
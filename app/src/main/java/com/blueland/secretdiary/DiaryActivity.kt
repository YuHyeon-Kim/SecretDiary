package com.blueland.secretdiary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blueland.secretdiary.databinding.ActivityDiaryBinding

class DiaryActivity : AppCompatActivity() {

    private val binding: ActivityDiaryBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_diary) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
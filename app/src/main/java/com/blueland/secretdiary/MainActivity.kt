package com.blueland.secretdiary

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import com.blueland.secretdiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    private var isChangePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNumberPicker()
        initListener()
    }

    private fun initNumberPicker() {
        binding.apply {

            numberPicker0.minValue = 0
            numberPicker0.maxValue = 9

            numberPicker1.minValue = 0
            numberPicker1.maxValue = 9

            numberPicker2.minValue = 0
            numberPicker2.maxValue = 9

        }
    }

    private fun initListener() {
        binding.apply {

            btnOpen.setOnClickListener {

                if (isChangePasswordMode) {
                    Toast.makeText(this@MainActivity, "비밀번호 변경 중 입니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
                val passwordFromUser = "${numberPicker0.value}${numberPicker1.value}${numberPicker2.value}"

                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                    // TODO 다이어리 페이지 작성 후 Intent
                } else {
                    showErrorAlertDialog()
                }
            }

            btnChangePassword.setOnClickListener {

                val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
                val passwordFromUser = "${numberPicker0.value}${numberPicker1.value}${numberPicker2.value}"

                if (isChangePasswordMode) {

//                    passwordPreferences.edit {
//                        val passwordFromUser = "${numberPicker0.value}${numberPicker1.value}${numberPicker2.value}"
//                        putString("password", passwordFromUser)
//                        commit()
//                    }
                    passwordPreferences.edit(true) {
                        putString("password", passwordFromUser)
                    }

                    isChangePasswordMode = false
                    btnChangePassword.setBackgroundColor(Color.BLACK)

                } else {

                    if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {

                        isChangePasswordMode = true
                        Toast.makeText(this@MainActivity, "변경할 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                        btnChangePassword.setBackgroundColor(Color.RED)

                    } else {
                        showErrorAlertDialog()
                    }
                }
            }
        }
    }

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호가 잘 못 되었습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }
}
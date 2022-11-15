package com.example.memberhoopoe

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.memberhoopoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun viewOnClick(view: View?) {
        when (view?.id) {
            R.id.btn_signIn -> {
                //데이터베이스를 생성해서 SQLdatabase connection 가져온다
                val dbHelper = DBHelper(applicationContext, "memberDB.db", null, 1)
                var flag = false
                val id = binding.edtID.text.toString()
                val password = binding.edtPW.text.toString()

                if (id.length == 0 || password.length == 0) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    flag = false
                }

                if (dbHelper.selectLogin(id, password) == true) {
                    flag = true
                }
                if (flag == true) {
                    val intent = Intent(applicationContext, SingInActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btn_signUp -> {
                val intent = Intent(applicationContext, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_regBack -> {
            }
        }
    }
}
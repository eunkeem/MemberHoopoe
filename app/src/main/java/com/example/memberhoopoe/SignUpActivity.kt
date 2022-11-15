package com.example.memberhoopoe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memberhoopoe.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    var flag: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //패스워드체크에서 커서가 넘어갈때 패스워드 값과 같은지 체크
        binding.edtRegPWCheck.setOnFocusChangeListener { view, b -> }

        binding.btnRegSave.setOnClickListener {
            //데이터베이스를 생성해서 SQLdatabase connection 가져온다
            val dbHelper = DBHelper(applicationContext, "memberDB.db", null, 1)

            val id = binding.edtRegID.text.toString()
            val password = binding.edtRegPW.text.toString()
            val password_check = binding.edtRegPWCheck.text.toString()
            val email = binding.edtRegEmail.text.toString()

            //데이터를 모두 입력했는지
            if (id.length == 0 || password.length == 0 || password_check.length == 0 || email.length == 0
            ) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                flag = false
            }
            //password와 passwordCheck가 일치하는지 체크
            if (!password.equals(password_check)) {
                Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()
                flag = false
            }
            //아이디가 중복되는지
            if (dbHelper.selectCheckID(id)) {
                Toast.makeText(this, "You can not use this ID", Toast.LENGTH_SHORT).show()
                flag = false
            } else {
                flag = true
            }
            if (!dbHelper.insert(id, password, email)) {
                Toast.makeText(this, "Member registration error", Toast.LENGTH_SHORT).show()
            }
            if (flag != false) {
                finish()
            }
            dbHelper.close()
        }//btnRegSave event handler

        binding.btnRegBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
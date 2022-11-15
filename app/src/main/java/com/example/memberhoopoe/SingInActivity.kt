package com.example.memberhoopoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SingInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)
    }
    fun viewOnClick(view: View?){
        when(view?.id){
            R.id.btn_delete_member ->{
//                val intent = Intent(this, DeleteActivity::class.java)
//                startActivity(intent)
            }
            R.id.btn_update_memberInfo ->{}
        }
    }
}
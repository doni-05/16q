package com.example.a16

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge 
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailEditText=findViewById(R.id.editTextNumberPassword)
        passwordEditText=findViewById(R.id.editTextNumberPassword)
    }
    fun onTextButtonClick(view: View) {
        // Обработка нажатия на текстовую кнопку
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
    }
    fun sign_in(view: View){
        val email=emailEditText.text.toString()
        val password=passwordEditText.text.toString()
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "email или пароль пустой", Toast.LENGTH_SHORT).show()

        }
        else{
            val intent = Intent(this, main::class.java)
            startActivity(intent)
        }
    }
}
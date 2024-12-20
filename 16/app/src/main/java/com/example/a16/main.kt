package com.example.a16

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class main : AppCompatActivity() {
    private lateinit var button1: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1=findViewById(R.id.imageView5)
    }
    fun imagebutton(view: View){

        val intent=
            Intent(this,menu::class.java)
        startActivity(intent)
    }
    fun imagebutton_ellipse(view: View){

        val intent=Intent(this,Profile::class.java)
        startActivity(intent)
    }
}
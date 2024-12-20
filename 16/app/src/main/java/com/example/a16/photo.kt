package com.example.a16

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PhotoActivity : AppCompatActivity() {

    private lateinit var fullImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_activity)

        fullImageView = findViewById(R.id.fullImageView)

        val imageUriString = intent.getStringExtra("imageUri")
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            fullImageView.setImageURI(imageUri)
        }
    }

    fun close(view: View) {
        setResult(RESULT_CANCELED)
        finish()
    }

    fun delete(view: View) {
        fullImageView.setImageDrawable(null) // Удаляем фотографию из ImageView
        Toast.makeText(this, "Фотография удалена", Toast.LENGTH_SHORT).show()
        val resultIntent = Intent()
        resultIntent.putExtra("deletedImageUri", intent.getStringExtra("imageUri"))
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
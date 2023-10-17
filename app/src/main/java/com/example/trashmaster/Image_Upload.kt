package com.example.trashmaster

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class Image_Upload : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)

        val image=findViewById<ImageView>(R.id.img1)

//        val uri:Uri =intent.getParcelableExtra("img")!!
//        image.setImageURI(uri)
        val bit=intent.getParcelableExtra<Bitmap>("bit")
        image.setImageBitmap(bit)

    }
}
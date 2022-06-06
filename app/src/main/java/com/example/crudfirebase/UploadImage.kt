package com.example.crudfirebase

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.Transaction
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.net.URL
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class UploadImage : AppCompatActivity() {
    lateinit var imageUri: Uri
    lateinit var ivGambar: ImageView
    lateinit var btnUploadImage: Button

    //--------------------------------------
    private lateinit var executor : Executor
    private lateinit var handler: Handler
    private var image : Bitmap? = null
    //--------------------------------------

    //Untuk penggunaan storage Firebase
    lateinit var uploadTask: UploadTask
    lateinit var fStor: FirebaseStorage
    lateinit var sRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        ivGambar = findViewById(R.id.ivGambar)
        btnUploadImage = findViewById(R.id.btnSelectImage)
        fStor = FirebaseStorage.getInstance("gs://fir-2022-ef8a1.appspot.com")
        sRef = fStor.reference

        btnUploadImage.setOnClickListener {
            selectImage()
        }
        executor = Executors.newSingleThreadExecutor()
        handler = Handler(Looper.getMainLooper())
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data != null) {
            imageUri = data.data!!
            ivGambar.setImageURI(imageUri)
            btnUploadImage.text = "Upload Image"

            btnUploadImage.setOnClickListener {
                uploadImage(imageUri)
            }
        }
    }

    private fun uploadImage(imageUri: Uri) {
        val UUID = UUID.randomUUID().toString()
        val imageRef = sRef.child("upload/images/${UUID}.jpg")
        uploadTask = imageRef.putFile(imageUri)

        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                Log.d("URL", downloadUri.toString())
                Toast.makeText(this, "gambar berhasil di upload", Toast.LENGTH_LONG).show()
                ivGambar.setImageResource(R.mipmap.ic_launcher_round)
                btnUploadImage.text = "Select Image"
                btnUploadImage.setOnClickListener {
                    selectImage()
                }
                getBitmapFromUrl(downloadUri.toString())
            } else {
                Toast.makeText(this, "Gambar gagal di upload", Toast.LENGTH_LONG).show()
                ivGambar.setImageResource(R.mipmap.ic_launcher_round)
                btnUploadImage.text = "Select Image"
                btnUploadImage.setOnClickListener {
                    selectImage()
                }
            }
        }

    }

    private fun getBitmapFromUrl (src: String){
        executor.execute {
            val imageURL = src
            try {
                val ins = URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(ins)

                handler.post{
                    ivGambar.setImageBitmap(image)
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
package com.example.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etName : EditText
    private lateinit var btnSave : Button
    private lateinit var mDBRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        etEmail = findViewById(R.id.etEmail)
        etName = findViewById(R.id.etNama)
        btnSave = findViewById(R.id.btnSave)

        mDBRef = FirebaseDatabase.getInstance().reference

        btnSave.setOnClickListener {
            saveData()
        }
    }

    fun saveData() {
        var name = etName.text.toString()
        var email = etEmail.text.toString()
        var uuid = UUID.randomUUID().toString()
        mDBRef.child("user").child((uuid))
            .setValue(User(name,email))
        Toast.makeText(this, "Data berhasil disimpan",Toast.LENGTH_SHORT).show()
        etName.text.clear()
        etEmail.text.clear()
        Log.i("DatabaseNama","Data berhasil dikirim ke firebase")
    }
}

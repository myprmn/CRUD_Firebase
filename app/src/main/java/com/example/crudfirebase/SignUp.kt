package com.example.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var etNameSignUp: EditText
    lateinit var etEmailSignUp: EditText
    lateinit var etPasswordSignUp: EditText
    lateinit var btnSignUp: Button
    lateinit var DbSignUp : DatabaseReference
    lateinit var btnIntent : Button

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etNameSignUp = findViewById(R.id.etNamaSignUp)
        etEmailSignUp = findViewById(R.id.etEmailSignUp)
        etPasswordSignUp = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnIntent = findViewById(R.id.btnIntent)
        mAuth = FirebaseAuth.getInstance()
        DbSignUp = FirebaseDatabase.getInstance().reference

        btnIntent.setOnClickListener {
            startActivity(Intent(this,SignIn::class.java))
        }
        btnSignUp.setOnClickListener {
            val name = etNameSignUp.text.toString()
            val email = etEmailSignUp.text.toString()
            val password = etPasswordSignUp.text.toString()
//            val password = etPasswordSignUp.toString()

            if (name != "" && email != "" && password != "") {
                signUp(email, password)
                //                saveSignUp()
            } else {
                Toast.makeText(this, "Masih ada field yang kosong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signUp(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val name = etNameSignUp.text.toString()
                    val email = etEmailSignUp.text.toString()

                    val uuid = mAuth.uid.toString()
                    DbSignUp.child("sign upss").child((uuid))
                        .setValue(DataModel(name,email,uuid))
                    Toast.makeText(this, "SignUp Berhasil", Toast.LENGTH_SHORT).show()
                    Log.i("Database", "Sign Up berhasil, dan tersimpan di database realtime")
                    etNameSignUp.text.clear()
                    etEmailSignUp.text.clear()
                    etPasswordSignUp.text.clear()
                } else {
                    Toast.makeText(this, "SignUp Gagal", Toast.LENGTH_SHORT).show()
                    Log.e("Database", "SignUp Gagal")
                }
            }
    }
}


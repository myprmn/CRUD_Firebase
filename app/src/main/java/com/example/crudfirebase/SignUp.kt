package com.example.crudfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    lateinit var etNameSignUp: EditText
    lateinit var etEmailSignUp: EditText
    lateinit var etPasswordSignUp: EditText
    lateinit var btnSignUp: Button

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etNameSignUp = findViewById(R.id.etNamaSignUp)
        etEmailSignUp = findViewById(R.id.etEmailSignUp)
        etPasswordSignUp = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        mAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val name = etNameSignUp.text.toString()
            val email = etEmailSignUp.text.toString()
            val password = etPasswordSignUp.toString()

            if (name != "" && email != "" && password != "") {
                signUp(email, password)
            } else {
                Toast.makeText(this, "Masih ada field yang kosong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "SignUp Berhasil", Toast.LENGTH_SHORT).show()
                    Log.i("Database", "Sign Up berhasil")
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


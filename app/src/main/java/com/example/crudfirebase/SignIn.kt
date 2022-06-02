package com.example.crudfirebase


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var btnLogIn: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText

    lateinit var auth: FirebaseAuth
    lateinit var dbSignIn: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar!!.hide()

        btnLogIn = findViewById(R.id.btnSignIn)
        etEmail = findViewById(R.id.etEmailSignIn)
        etPassword = findViewById(R.id.etPasswordSignIn)
        auth = FirebaseAuth.getInstance()
        dbSignIn = FirebaseDatabase.getInstance().reference

        btnLogIn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if (email !="" && password!=""){
//                Toast.makeText(this, "email : "+email+"password : "+password, Toast.LENGTH_LONG).show()
                DataLogin(email, password)


            } else {
                Toast.makeText(this, "Field masih kosong",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun DataLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(
                        this, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w("Sign In", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed. "+email.toString()+"-"+password.toString()+task.exception.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}
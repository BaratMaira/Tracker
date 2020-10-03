package com.example.tracker.signinMother

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tracker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup_mother.*

class SignupMother : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val database by lazy { FirebaseFirestore.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_mother)

        setupViews()
    }

    private fun setupViews(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        singupgobtn.setOnClickListener() {
            val username = editTextUsername.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPsswd.text.toString()
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { result ->
                    if (!result.isSuccessful) {
                        Toast.makeText(this, result.exception.toString(), Toast.LENGTH_LONG).show()

                        return@addOnCompleteListener
                    }

                    val id = firebaseAuth.currentUser?.uid
                    val user = hashMapOf(
                        "username" to username,
                        "email" to email,
                        "uid" to id
                    )
                    database.collection("users").add(user as Map<String, String>)
                    Toast.makeText(this, result.result?.user?.email, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, SigninMother::class.java))
                    finish()
                }
        }

        loginButton.setOnClickListener{
            startActivity(Intent(this, SigninMother::class.java))
            finish()
        }
    }
}

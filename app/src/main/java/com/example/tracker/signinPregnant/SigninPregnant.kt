package com.example.tracker.signinPregnant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tracker.pregnant.CalendarActivity
import com.example.tracker.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin_pregnant.*

class SigninPregnant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_pregnant)

        setupViews()
    }

    private fun setupViews(){

        signinGoBtn.setOnClickListener {
            val email = editTextEmailSignin.text.toString()
            val password = editTextPsswdSignin.text.toString()

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { result ->
                    if (!result.isSuccessful) {
                        Toast.makeText(this,
                            result.exception.toString(), Toast.LENGTH_LONG).show()

                        return@addOnCompleteListener
                    }
                    Toast.makeText(this, result.result?.user?.email,
                        Toast.LENGTH_LONG).show()

                    startActivity(Intent(this, CalendarActivity::class.java))
                    finish()
                }
        }

        signupButton.setOnClickListener(){
            startActivity(Intent(this, SignupPregnant::class.java))
            finish()
        }
    }
}
package com.example.tracker.signinMother

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tracker.R
import com.example.tracker.mother.MotherMainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin_mother.*

class SigninMother : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_mother)

        setupViews()
    }

    private fun setupViews(){
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if(currentUser != null){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }

        signingobtn.setOnClickListener {
            val email = editTextEmailMother.text.toString()
            val password = editTextPsswdMother.text.toString()

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

                    startActivity(Intent(this, MotherMainActivity::class.java))
                    finish()
                }
        }

        signupButton.setOnClickListener(){
            startActivity(Intent(this, SignupMother::class.java))
            finish()
        }
    }
}
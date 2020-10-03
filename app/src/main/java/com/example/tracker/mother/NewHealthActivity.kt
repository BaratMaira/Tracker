package com.example.tracker.mother

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tracker.R
import com.example.tracker.pregnant.TrackerActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_tracker.*
import java.util.*

class NewHealthActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val database by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_health)

        save.setOnClickListener {

            val widths = editTextWidth.text.toString()
            val height = editTextWidth1.text.toString()
            val timestamp = Date()

            firebaseAuth = FirebaseAuth.getInstance()

            val id = firebaseAuth.currentUser?.uid
            val health = hashMapOf(
                "uid" to id,
                "width" to widths,
                "height" to height,
                "timestamp" to timestamp
            )

            database.collection("health").add(health)
            startActivity(Intent(this, HealthActivity::class.java))
            finish()
        }
    }
}

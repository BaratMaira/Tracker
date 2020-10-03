package com.example.tracker.pregnant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat.startActivity
import com.example.tracker.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_tracker.*
import java.util.*

class NewTrackerActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val database by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tracker)

        save.setOnClickListener {

            val widths = editTextWidth.text.toString()
            val pressure = editTextWidth1.text.toString()
            val timestamp = Date()

            firebaseAuth = FirebaseAuth.getInstance()

            val id = firebaseAuth.currentUser?.uid
            val trackers = hashMapOf(
                "uid" to id,
                "width" to widths,
                "pressure" to pressure,
                "timestamp" to timestamp
            )

            database.collection("tracker").add(trackers)
            startActivity(Intent(this, TrackerActivity::class.java))
            finish()
        }
    }
}

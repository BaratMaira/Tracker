package com.example.tracker.pregnant

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracker.R
import com.example.tracker.adapter.TrackerAdapter
import com.example.tracker.pregnant.model.Tracker
import com.example.tracker.pregnant.model.User
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.android.synthetic.main.activity_tracker.*
import java.util.*
import kotlin.collections.ArrayList

class TrackerActivity : AppCompatActivity() {
//    var trackers: ArrayList<Tracker> = ArrayList()
//    private lateinit var trackerAdapter: TrackerAdapter
//    private val auth by lazy { FirebaseAuth.getInstance() }
//    private val database by lazy { FirebaseFirestore.getInstance() }

//    @TargetApi(Build.VERSION_CODES.O)
//    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)

        setSupportActionBar(toolbar)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        fillMessages()
//        recyclerView_tracker!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        trackerAdapter = TrackerAdapter(trackers)
//        recyclerView_tracker!!.adapter = trackerAdapter
//        (trackerAdapter as TrackerAdapter).update(trackers)

        add.setOnClickListener{
            startActivity(Intent(this, NewTrackerActivity::class.java))
            finish()
        }
    }


//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun fillMessages() {
//        val coll = database.collection("tracker")
//        coll.orderBy("date").addSnapshotListener { snapshot, e ->
//            var temparray = ArrayList<Tracker>()
//            for (document in snapshot!!.documents) {
//                val senderId = document.get("uid").toString()
//                val width = document.get("width").toString()
//                val pressure = document.get("pressure").toString()
//                val date = document.get("date") as Double
//
//                val convertedDate = getStringTime(date.toLong())
//                database.collection("users")
//                    .whereEqualTo("uid", senderId)
//                    .addSnapshotListener { snapshot1, e1 ->
//                        for (document1 in snapshot1!!.documents) {
//                            snapshot1.documents[0]
//                            val data = document1.data
//                            if (data != null) {
//                                val username = data["username"].toString()
//                                val id = data["uid"].toString()
//                                val email = data["email"].toString()
//                                Log.d("Taaag", data.toString())
//                                val user = User(id = id, username = username, email = email)
//                                val tracker =
//                                    Tracker(user = user, width = width,pressure = pressure, timestamp = convertedDate)
//                                temparray.add(tracker)
//                            }
//                        }
//                        trackers.clear()
//                        trackers.addAll(temparray)
//                        trackerAdapter?.notifyDataSetChanged()
//                    }
//            }
//        }
//    }
//    }
//
//@RequiresApi(Build.VERSION_CODES.N)
//    private fun getStringTime(time: Long): String {
//        return try {
//            val DF = SimpleDateFormat("dd.MM.yyyy hh:mm")
//            val date = Date(time * 1000)
//            DF.format(date)
//        } catch (e: Exception) {
//            e.toString()
//        }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    }


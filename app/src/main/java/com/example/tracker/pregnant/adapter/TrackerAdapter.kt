package com.example.tracker.adapter
import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.tracker.R
import com.example.tracker.pregnant.model.Tracker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class TrackerAdapter(private val trackers: ArrayList<Tracker>) :
    RecyclerView.Adapter<TrackerAdapter.ViewHolder>() {
    private val database by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private var s = ""
    fun update(new_messages: ArrayList<Tracker>){
        this.trackers.clear()
        trackers.addAll(new_messages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.tracker_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return trackers.size
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.trackerDate.text = trackers[position].timestamp
        holder.tracker_width.text = trackers[position].width
        holder.desc.text = trackers[position].pressure
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var trackerDate: TextView = itemView.findViewById(R.id.trackerDate)
        var tracker_width: TextView = itemView.findViewById(R.id.tracker_width)
        var desc: TextView = itemView.findViewById(R.id.desc)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getStringTime(time: Long): String {
        return try {
            val DF = SimpleDateFormat("dd.MM.yyyy hh:mm")
            val date = Date(time * 1000)
            DF.format(date)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun getUserName(senderId: String): String{
        database.collection("users").whereEqualTo("uid", senderId).get()
            .addOnSuccessListener{ querySnapshot ->
                s = querySnapshot.documents[0].get("username").toString()
            }
        return s
    }

}
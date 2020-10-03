package com.example.tracker.pregnant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.pregnant.model.Doctor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.doctors_list.view.*

class DoctorAdapter (
    private var doctors: ArrayList<Doctor>,
    private val onClick: (Doctor) -> Unit)
    : RecyclerView.Adapter<DoctorAdapter.CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R
                        .layout.doctors_list, parent, false)
        )

    override fun getItemCount() = doctors.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(doctors[position])
    }

    inner class CategoriesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(doctor: Doctor){
            with(view){
                doctor_name.text = doctor.name
                doctor_position.text = doctor.position
                Picasso.get().load(doctor.image).into(view.doctor_image)

                setOnClickListener{
                    onClick(doctor)
                }
//                hospital_phone.setOnClickListener {
//                    val intent = Intent(Intent.ACTION_DIAL)
//                    intent.setData(Uri.parse("tel:" + doctor.telephone))
//                    view.context.startActivity(intent)
//                }
            }
        }
    }
}
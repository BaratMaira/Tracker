package com.example.tracker.pregnant.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.CardActivity
import com.example.tracker.R
import com.example.tracker.pregnant.model.Doctor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.doctor_detail_list.view.*
import android.app.Activity
import com.example.tracker.pregnant.DoctorDetailsActivity


class DoctorDetailAdapter (
    private var doctors: ArrayList<Doctor>,
    private val onClick: (Doctor) -> Unit)
    : RecyclerView.Adapter<DoctorDetailAdapter.CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    com.example.tracker.R
                        .layout.doctor_detail_list, parent, false)
        )

    override fun getItemCount() = doctors.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(doctors[position])
    }

    inner class CategoriesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(doctor: Doctor){
            with(view){
                name_detail.text = doctor.name
                position_detail.text = doctor.position
                Picasso.get().load(doctor.image).into(view.poster_path)
                description_detail.text = doctor.description
                day.text = doctor.monday
                day1.text = doctor.thursday
                day2.text = doctor.friday

                pay.setOnClickListener{
                    val intent = Intent(view.context, CardActivity::class.java)
                    view.context.startActivity(intent)
                    (context as DoctorDetailsActivity).finish()
                }
                pay1.setOnClickListener{
                    val intent = Intent(view.context, CardActivity::class.java)
                    view.context.startActivity(intent)
                    (context as DoctorDetailsActivity).finish()
                }
                pay2.setOnClickListener{
                    val intent = Intent(view.context, CardActivity::class.java)
                    view.context.startActivity(intent)
                    (context as DoctorDetailsActivity).finish()
                }
            }
        }
    }
}
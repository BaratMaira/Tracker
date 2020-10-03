package com.example.tracker.pregnant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.pregnant.model.Hospital
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hospital_list.view.*

class ConsultationAdapter (
    private var hospitals: ArrayList<Hospital>,
    private val onClick: (Hospital) -> Unit)
    : RecyclerView.Adapter<ConsultationAdapter.CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R
                        .layout.hospital_list, parent, false)
        )

    override fun getItemCount() = hospitals.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(hospitals[position])
    }

    inner class CategoriesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(hospital: Hospital){
            with(view){
                hospital_name.text = hospital.strName
                hospital_address.text = hospital.strAddress
                Picasso.get().load(hospital.image).into(view.hospital_image)

                setOnClickListener{
                    onClick(hospital)
                }
            }
        }
    }
}
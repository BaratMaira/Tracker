package com.example.tracker.pregnant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.pregnant.model.Nutrition
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nutrition_list.view.*

class NutritionAdapter (
    private var nutritions: ArrayList<Nutrition>,
    private val onClick: (Nutrition) -> Unit)
    : RecyclerView.Adapter<NutritionAdapter.CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R
                        .layout.nutrition_list, parent, false)
        )

    override fun getItemCount() = nutritions.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(nutritions[position])
    }

    inner class CategoriesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(nutrition: Nutrition){
            with(view){
                nutrition_title.text = nutrition.title
                Picasso.get().load(nutrition.image).into(view.nutrition_image)

                setOnClickListener{
                    onClick(nutrition)
                }
            }
        }
    }
}
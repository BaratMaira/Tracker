package com.example.tracker.pregnant.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.pregnant.model.Nutrition
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nutrition_detail_list.view.*

class NutritionDetailsAdapter(
    private var nutritions: ArrayList<Nutrition>, private val onClick: (Nutrition) -> Unit)
    : RecyclerView.Adapter<NutritionDetailsAdapter.MealDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MealDetailViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R
                        .layout.nutrition_detail_list, parent, false)
        )
    override fun getItemCount() = nutritions.size


    override fun onBindViewHolder(holder: MealDetailViewHolder, position: Int) {
        holder.bind(nutritions[position])
    }

    inner class MealDetailViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(nutrition: Nutrition){
            with(view){
                meal_name.text = nutrition.title
                Picasso.get().load(nutrition.image).into(view.male_image_detail)
                instructions.text = nutrition.instruction

                youtube.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(nutrition.youtube))
                    view.context.startActivity(intent)
                }
            }
        }
    }
}
package com.example.tracker.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import kotlinx.android.synthetic.main.slide_items.view.*

class IntroSliderAdapter(private val introSlides: List<IntroSlide>) :
RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        IntroSliderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.slide_items, parent, false)
        )

    override fun getItemCount () = introSlides.size

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    inner class IntroSliderViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(introSlide: IntroSlide){
            view.textTitle.text = introSlide.title
            view.textDescription.text = introSlide.description
            view.imageSlideIcon.setImageResource(introSlide.icon)
        }
    }
}
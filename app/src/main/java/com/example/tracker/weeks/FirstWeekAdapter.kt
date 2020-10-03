package com.example.tracker.weeks


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tracker.R
import kotlinx.android.synthetic.main.slider_weeks.view.*

class FirstWeekAdapter(
    private val firstWeek: List<WeekItem>,
    viewPager: ViewPager2
) :
    RecyclerView.Adapter<FirstWeekAdapter.FirstWeekViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FirstWeekViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.slider_weeks, parent, false)
        )

    override fun getItemCount () = firstWeek.size

    override fun onBindViewHolder(holder: FirstWeekViewHolder, position: Int) {
        holder.bind(firstWeek[position])
    }

    inner class FirstWeekViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(firstWeek: WeekItem){
            view.imageSlide.setImageResource(firstWeek.image)
        }
    }
}
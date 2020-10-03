package com.example.tracker.mother.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.mother.model.Search
import kotlinx.android.synthetic.main.pain_list.view.*

class SearchAdapter (
    private var alergies1: ArrayList<Search>)
    : RecyclerView.Adapter<SearchAdapter.CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R
                        .layout.pain_list, parent, false)
        )

    override fun getItemCount() = alergies1.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(alergies1[position])
    }

    inner class CategoriesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(search: Search){
            with(view){
                search_name.text = search.name
                search_desc.text = search.description
            }
        }
    }
}
package com.example.tracker.mother.model

data class Search (
    val id: Int,
    val name: String,
    val description:String
)

data class SearchResponse(
    val alergies1: List<Search>
)
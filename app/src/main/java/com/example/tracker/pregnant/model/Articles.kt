package com.example.tracker.pregnant.model

data class Articles(
    val id:Int,
    val title:String,
    val overview:String,
    val image:String
)

data class ArticlesResponse(
    val articles: List<Articles>
)
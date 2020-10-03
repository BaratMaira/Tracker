package com.example.tracker.pregnant.model

data class Nutrition(
    val id: Int,
    val title: String,
    val image: String,
    val youtube: String,
    val instruction:String,
    val ingredients:String
)

data class NutritionResponse(
    val nutritions:List<Nutrition>
)

data class NutritionResponses(
    val nutritionn: List<Nutrition>
)

data class NutritionResponseDetail(
    val nutrition1:List<Nutrition>
)
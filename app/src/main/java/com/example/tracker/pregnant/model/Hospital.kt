package com.example.tracker.pregnant.model

data class Hospital (
    val id: Int,
    val strName: String,
    val image: String,
    val strAddress: String
)

data class HospitalResponse(
    val hospitals: List<Hospital>
)
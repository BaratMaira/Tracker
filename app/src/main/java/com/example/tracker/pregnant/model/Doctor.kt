package com.example.tracker.pregnant.model

data class Doctor(
    val id: Int,
    val name: String,
    val position: String,
    val telephone: String,
    val hospitalId: Int,
    val image:String,
    val description:String,
    val monday:String,
    val thursday:String,
    val friday:String
)

data class DoctorResponse(
    val doctors: List<Doctor>
)
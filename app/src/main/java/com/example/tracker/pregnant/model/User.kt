package com.example.tracker.pregnant.model

data class User (
    val id: String,
    val email: String,
    val username: String
){
    constructor(): this("", "", "")
}
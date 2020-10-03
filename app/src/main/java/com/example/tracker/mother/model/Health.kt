package com.example.tracker.mother.model

import com.example.tracker.pregnant.model.User

data class Health(
    val width: String,
    val height: String,
    val user: User?,
    val timestamp:  String
) {
    constructor(): this("","",null,"")
}
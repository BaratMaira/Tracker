package com.example.tracker.pregnant.model

import java.util.*

data class Tracker(
    val width: String,
    val pressure: String,
    val user: User?,
    val timestamp:  String
) {
    constructor(): this("","",null,"")
}
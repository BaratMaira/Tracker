package com.example.tracker.pregnant.model

data class Message(
    val user: User?,
    val text: String,
    val timestamp: String
) {
    constructor(): this(null,"","")
}
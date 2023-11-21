package com.saidmuratozdemir.notificationtestapp.model

data class FirebaseConfig(
    val url: String,
    val projectId: String,
    val email: String,
    val apiKey: String
) {
    companion object {
        fun fromString(string: String): FirebaseConfig {
            val split = string.split(" ")
            return FirebaseConfig(split[0], split[1], split[2], split[3])
        }
    }
}

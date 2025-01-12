package com.example.myapp.util

data class Message<T>(
    val type: String,
    val data: T
) {
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to type,
            "data" to data
        )
    }
}

object MessageType {
    const val GREETING = "GREETING"
    const val HEARTBEAT = "HEARTBEAT"
    const val CREATE = "CREATE"
    const val DELETE = "DELETE"
    const val UPDATE = "UPDATE"
    const val GET_ALL = "GET_ALL"
    const val ERROR = "ERROR"
}

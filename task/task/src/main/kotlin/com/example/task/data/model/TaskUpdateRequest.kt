package com.example.task.data.model

import com.example.task.data.Priority

data class TaskUpdateRequest(
    val description: String?, val isReminderSet: Boolean?, val isTaskOpen: Boolean?, val priority: Priority?
)

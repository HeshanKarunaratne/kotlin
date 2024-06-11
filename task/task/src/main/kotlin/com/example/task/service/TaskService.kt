package com.example.task.service

import com.example.task.data.Task
import com.example.task.data.model.TaskCreateRequest
import com.example.task.data.model.TaskDto
import com.example.task.data.model.TaskUpdateRequest
import com.example.task.exception.BadRequestException
import com.example.task.exception.TaskNotFoundException
import com.example.task.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class TaskService(private val repository: TaskRepository) {

    private fun mappingEntityToDto(task: Task): TaskDto {
        return TaskDto(
            task.id, task.description, task.isReminderSet, task.isTaskOpen, task.createdOn, task.priority
        )
    }

    private fun mappingFromRequestToEntity(task: Task, request: TaskCreateRequest) {
        task.description = request.description
        task.isReminderSet = request.isReminderSet
        task.isTaskOpen = request.isTaskOpen
        task.priority = request.priority
    }

    private fun checkTaskForId(id: Long) {
        if (!repository.existsById(id)) {
            throw TaskNotFoundException("Task with the ID: $id does not exist!")
        }
    }

    fun getTaskById(id: Long): TaskDto {
        checkTaskForId(id)
        val task: Task = repository.findTasksById(id)
        return mappingEntityToDto(task)
    }

    fun getAllTasks(): List<TaskDto> =
        repository.findAll().stream().map { mappingEntityToDto(it) }.collect(Collectors.toList())

    fun getAllOpenTasks(): List<TaskDto> =
        repository.queryAllOpenTasks().stream().map { mappingEntityToDto(it) }.collect(Collectors.toList())

    fun getAllClosedTasks(): List<TaskDto> =
        repository.queryAllClosedTasks().stream().map { mappingEntityToDto(it) }.collect(Collectors.toList())

    fun createTask(request: TaskCreateRequest): TaskDto {
        if (repository.doesDescriptionExist(description = request.description)) {
            throw BadRequestException("There is already a task with description: ${request.description}")
        }
        val task = Task()
        mappingFromRequestToEntity(task, request)
        val savedTask: Task = repository.save(task)
        return mappingEntityToDto(savedTask)
    }

    fun updateTask(id: Long, request: TaskUpdateRequest): TaskDto {
        checkTaskForId(id)
        val existingTask: Task = repository.findTasksById(id)
        for (prop in TaskUpdateRequest::class.memberProperties) {
            if (prop.get(request) != null) {
                val field: Field? = ReflectionUtils.findField(Task::class.java, prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingTask, prop.get(request))
                }
            }
        }
        val savedTask: Task = repository.save(existingTask)
        return mappingEntityToDto(savedTask)
    }

    fun deleteTask(id: Long): String {
        checkTaskForId(id)
        repository.deleteById(id)
        return "Task with the ID: $id has been deleted"
    }
}
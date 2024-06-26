package com.example.task.service

import com.example.task.data.Priority
import com.example.task.data.Task
import com.example.task.data.model.TaskCreateRequest
import com.example.task.data.model.TaskDto
import com.example.task.data.model.TaskUpdateRequest
import com.example.task.exception.BadRequestException
import com.example.task.exception.TaskNotFoundException
import com.example.task.repository.TaskRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime


@ExtendWith(MockKExtension::class)
class TaskServiceTest {

    @RelaxedMockK
    private lateinit var mockRepository: TaskRepository

    @InjectMockKs
    private lateinit var objectUnderTest: TaskService

    private val task = Task()
    private lateinit var createRequest: TaskCreateRequest
    private val taskId: Long = 543

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        createRequest = TaskCreateRequest(
            "test", isReminderSet = false, isTaskOpen = false, createdOn = LocalDateTime.now(), priority = Priority.LOW
        )
    }

    @Test
    fun `when all tasks get fetched then check if the given size is correct`() {
        val expectedTask: List<Task> = listOf(Task(), Task())
        every { mockRepository.findAll() } returns expectedTask.toMutableList()
        val actualTasks: List<TaskDto> = objectUnderTest.getAllTasks()
        assertThat(actualTasks.size).isEqualTo(expectedTask.size)
    }

    @Test
    fun `when task is created then check for the task properties`() {
        task.description = createRequest.description
        task.isTaskOpen = createRequest.isTaskOpen
        task.priority = createRequest.priority
        every { mockRepository.save(any()) } returns task
        val actualTaskDto: TaskDto = objectUnderTest.createTask(createRequest)
        assertThat(actualTaskDto.description).isEqualTo(task.description)
        assertThat(actualTaskDto.isTaskOpen).isEqualTo(task.isTaskOpen)
        assertThat(actualTaskDto.priority).isEqualTo(task.priority)
    }

    @Test
    fun `when task description already exists then check for bad request exception`() {
        every { mockRepository.doesDescriptionExist(any()) } returns true
        val exception = assertThrows<BadRequestException> { objectUnderTest.createTask(createRequest) }
        assertThat(exception.message).isEqualTo("There is already a task with description: ${createRequest.description}")
//        verify { mockRepository.save(any()) wasNot called }
    }

    @Test
    fun `when get task by id is called then expect a task not found exception`() {
        every { mockRepository.existsById(any()) } returns false
        val exception: TaskNotFoundException =
            assertThrows<TaskNotFoundException> { objectUnderTest.getTaskById(taskId) }
        assertThat(exception.message).isEqualTo("Task with the ID: $taskId does not exist!")
    }

    @Test
    fun `when all open tasks are fetched check the property of first item`() {
        task.isTaskOpen = false
        val expectedTasks = listOf(task)
        every { mockRepository.queryAllOpenTasks() } returns expectedTasks.toMutableList()
        val actualList: List<TaskDto> = objectUnderTest.getAllOpenTasks()
        assertThat(actualList[0].isTaskOpen).isEqualTo(expectedTasks[0].isTaskOpen)
    }

    @Test
    fun `when save task is called then check if argument could be captured`() {
        val taskSlot = slot<Task>()
        task.description = createRequest.description
        task.isTaskOpen = createRequest.isTaskOpen
        task.isReminderSet = createRequest.isReminderSet
        task.priority = createRequest.priority

        every { mockRepository.save(capture(taskSlot)) } returns task
        val actualTaskDto: TaskDto = objectUnderTest.createTask(createRequest)
        verify { mockRepository.save(capture(taskSlot)) }
        assertThat(taskSlot.captured.description).isEqualTo(actualTaskDto.description)
        assertThat(taskSlot.captured.isReminderSet).isEqualTo(actualTaskDto.isReminderSet)
        assertThat(taskSlot.captured.isTaskOpen).isEqualTo(actualTaskDto.isTaskOpen)
    }

    @Test
    fun `when get task by id is called then check for a specific description`() {
        task.description = "Hi"
        every { mockRepository.existsById(any()) } returns true
        every { mockRepository.findTasksById(any()) } returns task
        val actualTaskDto: TaskDto = objectUnderTest.getTaskById(taskId)
        assertThat(actualTaskDto.description).isEqualTo(task.description)
    }

    @Test
    fun `when get task by id is called then check if argument could be captured`() {
        val taskIdSlot = slot<Long>()
        every { mockRepository.existsById(any()) } returns true
        every { mockRepository.findTasksById(capture(taskIdSlot)) } returns task
        objectUnderTest.getTaskById(taskId)
//        verify { mockRepository.findById(capture(taskIdSlot)) }
        assertThat(taskIdSlot.captured).isEqualTo(taskId)
    }

    @Test
    fun `when delete task is called then check the response message`() {
        every { mockRepository.existsById(any()) } returns true
        val actualMessage: String = objectUnderTest.deleteTask(taskId)

        assertThat(actualMessage).isEqualTo("Task with the ID: $taskId has been deleted")
    }

    @Test
    fun `when delete task is called then check if argument could be captured`() {
        val taskIdSlot = slot<Long>()

        every { mockRepository.existsById(any()) } returns true
        every { mockRepository.deleteById(capture(taskIdSlot)) } returns Unit
        objectUnderTest.deleteTask(taskId)

        verify { mockRepository.deleteById(capture(taskIdSlot)) }
        assertThat(taskIdSlot.captured).isEqualTo(taskId)
    }

    @Test
    fun `when update task is called then check for the request properties`() {
        task.description = "Go to Sawarma restaurant"
        task.isReminderSet = false
        task.isTaskOpen = true
        task.priority = Priority.MEDIUM

        val request = TaskUpdateRequest(
            task.description, task.isReminderSet, task.isTaskOpen, task.priority
        )

        every { mockRepository.existsById(any()) } returns true
        every { mockRepository.findTasksById(any()) } returns task
        every { mockRepository.save(any()) } returns task
        val actualTaskDto: TaskDto = objectUnderTest.updateTask(taskId, request)

        assertThat(actualTaskDto.id).isEqualTo(task.id)
        assertThat(actualTaskDto.description).isEqualTo(task.description)
        assertThat(actualTaskDto.isReminderSet).isEqualTo(task.isReminderSet)
        assertThat(actualTaskDto.isTaskOpen).isEqualTo(task.isTaskOpen)
        assertThat(actualTaskDto.priority).isEqualTo(task.priority)
        assertThat(actualTaskDto.createdOn).isEqualTo(task.createdOn)
    }
}
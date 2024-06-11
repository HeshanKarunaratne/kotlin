package com.example.task.repository

import com.example.task.data.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql

@DataJpaTest(properties = ["spring.jpa.properties.javax.persistence.validations.mode=none"])
class TaskRepositoryEmbeddedTest {
    @Autowired
    private lateinit var taskRepository: TaskRepository

    @Autowired
    private lateinit var objectUnderTest: TaskRepository
    private val numberOfRecordsInTestDataSql = 3
    private val numberOfOpenRecordsInTestDataSql = 1
    private val numberOfClosedRecordsInTestDataSql = 2

    @Test
    @Sql("classpath:test-data.sql")
    fun `when task is saved then check for not null`() {
        val task: Task = objectUnderTest.findTasksById(111)
        assertThat(task).isNotNull
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when all tasks are fetched then check for the number of records`() {
        val tasks: List<Task> = objectUnderTest.findAll()
        assertThat(tasks.size).isEqualTo(numberOfRecordsInTestDataSql)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when task is deleted then check for the size of list`() {
        objectUnderTest.deleteById(111)
        val tasks: List<Task> = objectUnderTest.findAll()
        assertThat(tasks.size).isEqualTo(2)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when all open tasks are queries then check for the correct number of open tasks`() {
        val tasks: List<Task> = objectUnderTest.queryAllOpenTasks()
        assertThat(tasks.size).isEqualTo(numberOfOpenRecordsInTestDataSql)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when all closed tasks are queries then check for the correct number of open tasks`() {
        val tasks: List<Task> = objectUnderTest.queryAllClosedTasks()
        assertThat(tasks.size).isEqualTo(numberOfClosedRecordsInTestDataSql)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when description is queried then check if description is already exists`() {
        val existingDescription: Boolean = objectUnderTest.doesDescriptionExist("first test todo")
        val notExistingDescription: Boolean = objectUnderTest.doesDescriptionExist("fifth test todo")
        assertThat(existingDescription).isTrue()
        assertThat(notExistingDescription).isFalse()
    }
}
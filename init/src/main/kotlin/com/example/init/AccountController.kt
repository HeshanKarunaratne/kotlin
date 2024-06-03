package com.example.init

import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.*

data class ViewAccount(
    val id: Int, val name: String
)

data class CreateAccount(
    @field:Size(min = 2, max = 5)
    val name: String
)


@RestController
@RequestMapping("/accounts")
class AccountController {

    @GetMapping
    fun getAll(): Iterable<ViewAccount> = listOf(ViewAccount(id = 1, name = "First Name"))

    @PostMapping
    fun create(@Valid @RequestBody request: CreateAccount): ViewAccount = ViewAccount(id = 2, name = request.name)
}
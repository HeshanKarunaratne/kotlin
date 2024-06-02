package com.example.database

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.*

@Entity
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0, val name: String
)

data class ViewAccount(
    val id: Long, val name: String
)

fun Account.toView() = ViewAccount(id, name)

interface AccountRepository : CrudRepository<Account, Long> {
    fun findByNameStartingWith(prefix: String): Iterable<Account>

    @Query("SELECT a FROM Account a WHERE a.name LIKE concat('%', :suffix)")
    fun search(suffix: String): Iterable<Account>
}

data class CreateAccount(
    val name: String
)

@RestController
@RequestMapping("accounts")
class AccountsController(val accountRepository: AccountRepository) {

    @GetMapping
    fun findAll(): Iterable<ViewAccount> = accountRepository.search("Account").map { it.toView() }

    @PostMapping
    fun create(@RequestBody createAccount: CreateAccount): ViewAccount = accountRepository.save(
        Account(name = createAccount.name)
    ).toView()
}
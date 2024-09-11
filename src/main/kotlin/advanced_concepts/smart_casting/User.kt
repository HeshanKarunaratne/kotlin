package advanced_concepts.smart_casting

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class User {

    @OptIn(ExperimentalContracts::class)
    fun isAuthenticated(): Boolean {
        contract {
            returns(true) implies (this@User is Authenticated)
            returns(false) implies (this@User is Anonymous)
        }
        return this is Authenticated
    }

    class Anonymous() : User() {
        fun promptToSignIn() = println("Please sign in.")
    }

    class Authenticated(private val username: String) : User() {
        fun greet() = println("Welcome, $username")
    }
}

fun onScreenLoaded(user:User){
    if(user.isAuthenticated()) user.greet() else user.promptToSignIn()
}
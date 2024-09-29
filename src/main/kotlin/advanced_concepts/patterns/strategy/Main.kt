package advanced_concepts.patterns.strategy

typealias Validator = (String) -> Boolean

val emailValidator: Validator = { it.contains("@") && it.contains(".") }
val usernameValidator: Validator = { it.isNotEmpty() }
val passwordValidator: Validator = { it.length >= 8 }

class FormField(val name: String, private val value: String, private val validator: Validator) {
    fun isValid() = validator(value)
}

fun Validator.optional(): Validator = { it.isEmpty() || this(it) }

fun main() {
    val emailField = FormField("email", "test@example.com", emailValidator.optional())
    println(emailField.isValid())

    val usernameField = FormField("username", "user123", usernameValidator)
    println(usernameField.isValid())

    val passwordField = FormField("password", "password123", passwordValidator)
    println(passwordField.isValid())

}
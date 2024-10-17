#### Classes in Kotlin

### Regular Classes

```txt
Can have multiple classes in the same file. In kotlin classes bundles state and operation in the form of properties and functions.
```

```kt
import kotlin.math.PI

class Circle(private val radius: Double) {
    fun area() = 2 * PI * radius
}

class Square(private val size: Double) {
    fun area() = size * size
}

fun main() {
    val circle = Circle(10.0)
    println(circle.area())

    val square = Square(10.0)
    println(square.area())
}
```

### Local Class

```txt
We can create a class inside a function. Then it will be known as a local class.
This is a helper class which is only relevant to the implementation of that function
```

```kt
class StudentScore(val student: String, val score: Double)

fun printGrades(studentScoreList: List<StudentScore>) {
    class ExamResult(val student: String, val score: Double) {
        fun grade() = when {
            score > 85 -> "A"
            score > 75 -> "B"
            score > 65 -> "C"
            else -> "S"
        }
    }

    studentScoreList
        .stream()
        .map { ExamResult(it.student, it.score) }
        .forEach { println("Grade for ${it.student} is ${it.grade()}") }
}

fun main() {
    val studentScoreList =
        listOf(StudentScore("heshan", 90.0), StudentScore("dilan", 80.0), StudentScore("danu", 70.0))
    printGrades(studentScoreList)
}
```

### Nested Class

```txt
When we immediately declare a class inside another class it is known as a nested class.
Nested class is a good choice when it is uniquely related to the outer class or we can make it private so it will be a helper class related to the implementation of the outer class.By default nested class does not have access to the members of the outer class.
```

```kt
import java.awt.Color

class Canvas(params: Params = Params()) {
    val pixels = params.width * params.height

    class Params(
        val width: Int = 1920, val height: Int = 1080, val backgroundColor: Color = Color.BLACK
    )
}

fun main() {
    val params = Canvas.Params(640, 480, Color.BLUE)
}
```

### Inner Class

```txt
When we immediately declare an inner class inside another class it is known as a inner class. Regular nested class cannot access member variables of the outer class, but an inner class can access the outer class member variables.
```

```kt
class FavouriteStrings(val first: String, val second: String) : Iterable<String> {
    override fun iterator(): Iterator<String> = FavouriteStringIterator()

    inner class FavouriteStringIterator : Iterator<String> {
        private var currentIndex = 0
        override fun hasNext() = currentIndex < 2

        override fun next() = when (currentIndex) {
            0 -> first
            1 -> second
            else -> throw NoSuchElementException()
        }.also { currentIndex++ }

    }
}

fun main() {
    val favourites = FavouriteStrings("one", "two")
    for (favourite in favourites) {
        println(favourite)
    }
}
```

### Data Class

```txt
Used for storing immutable data. When you use the data modifier on a class it automatically gets the implementation of equals() and hashCode() which ensures that these are compared by values rather than the reference. Also kotlin will implement toString() method.
```

```kt
data class Book(val id: Int, val title: String, val author: String)

fun main() {
    val myBook = Book(256, "The Malt Shop Caper", "Slim Chancery")
    println(myBook)
    val myBookV2 = myBook.copy(author = "Heshan Karunaratne")
    println(myBookV2)
}
```

### Value Class

```txt
When you want to wrap a simple type with a strong domain model. You need to make sure to add @JvmInline annotation in front of the value class. Kotlin will optimize this so that the runtime performance will basically be the same as if they were underline String or Int types. Can only include exactly 1 property and it has to be read only. Its better to use value class with java primitives because primitives goes to stack rather than the heap.
```

```kt
@JvmInline
value class Id(val value: Int)

@JvmInline
value class Title(val value: String)

@JvmInline
value class Author(val value: String)

data class Book2(val id: Id, val title: Title, val author: Author)

fun main() {
    val myBook = Book2(Id(256), Title("The Malt Shop Caper"), Author("Slim Chancery"))
    println(myBook)
    val myBookV2 = myBook.copy(author = Author("Heshan Karunaratne"))
    println(myBookV2)
}
```

### Enum Class

```txt
Each instance is called an entry. Great for limiting the values to a set of known values
```

```kt
enum class OrderStatus {
    PREPARING, READY_FOR_PICKUP, OUT_FOR_DELIVERY, DELIVERED
}

fun getMessage(status: OrderStatus) = when (status) {
    OrderStatus.PREPARING -> "Working on it"
    OrderStatus.READY_FOR_PICKUP -> "Ready to ship"
    OrderStatus.OUT_FOR_DELIVERY -> "Delivering today"
    OrderStatus.DELIVERED -> "Delivered"
}

fun main() {
    OrderStatus.entries.forEach {
        println("${it.name}: ${it.ordinal}")
    }
    println(getMessage(status = OrderStatus.OUT_FOR_DELIVERY))
}
```

### Sealed Class

```txt
Great for constraining direct sub types to a set of known types
```

```kt
sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure(val ex: Exception) : ApiResponse<Nothing>()
    data object Processing : ApiResponse<Nothing>()
}
```

### Annotation Class

```txt
You can create your own annotation, You need to add general meta annotations as well
```

```kt
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class RequiresRoles(val role: String)
```

### Smart Casting

```kt
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

    class Authenticated(val username: String) : User() {
        fun greet() = println("Welcome, $username")
    }
}

fun onScreenLoaded(user: User) {
    if (user.isAuthenticated()) user.greet() else user.promptToSignIn()
}
```

- Limitations
    - returns - Can only be from below list
        - true
        - false
        - null
        - notNull
    - implies
        - x is Type
        - x !is Type
        - x == null
        - x != null

### Try-Catch and runCatching()

- try is an expression in kotlin

```kt
package advanced_concepts.try_catch

import java.time.LocalDate

fun main() {
    val result: Result<LocalDate> = runCatching { LocalDate.parse("2024-01-01") }

    result.onSuccess { date -> println(date.dayOfWeek) }
    result.onFailure { ex -> println("Unable to parse. ${ex.message}") }
}
```

### Variance

- Within a subtype return types can be more specific: Covariance
- Within a subtype parameter types can be more general: Contravariance

```kt
package advanced_concepts.generics_variance

open class Snack
class CandyBar : Snack()

open class Money
open class Coin : Money()
class Dime : Coin()
class Quarter : Coin()

fun interface VendingMachine {
    fun purchase(money: Coin): Snack
}

class SimpleVendingMachine1 : VendingMachine {
    override fun purchase(money: Coin): Snack = Snack()
}

class SimpleVendingMachine2 : VendingMachine {
    override fun purchase(money: Coin): CandyBar = CandyBar()
}

class SimpleVendingMachine3 : VendingMachine {
    private fun purchase(money: Money): CandyBar = CandyBar()
    override fun purchase(coin: Coin): Snack = purchase(coin as Money)
}
```

- Overloading properties with function types
- In below Money is the super type of Coin, this cannot be achieved using functions as in above

```kt
interface VendingPropMachine {
    val purchase: (Coin) -> Snack
}

class SimpleVendingPropMachine1 : VendingPropMachine {
    override val purchase: (Money) -> Snack = { CandyBar() }
}


interface VendingGenMachine<in T, out R>
class Test1 : VendingGenMachine<Coin, Snack>
class Test2 : VendingGenMachine<Money, CandyBar>
```

- R which is the return type uses Covariance (Can use a specific type instead of general case)
- T which is the input parameter type uses Contravariance (Can use the parent(general case))
- Above contracts are type safe as well

### Type Projections

```txt
Product
  ^
  |
Snack
  ^
  |
CandyBar
```

```kt
// out projection

class VendingTypeProjectionMachine<T : Snack>(private val product: T) {
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Coin()
}

val candyBarMachine: VendingTypeProjectionMachine<CandyBar> = VendingTypeProjectionMachine(CandyBar())
val snackMachine: VendingTypeProjectionMachine<out Snack> = candyBarMachine

// Here T converts to Nothing type and it is the subtype of every other type that will encounter
//val result = snackMachine.refund(product: T)

```

```kt
// in projection
class VendingTypeProjectionMachine<T : Snack>(private val product: T) {
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Coin()
}

val snackMachine: VendingTypeProjectionMachine<Snack> = VendingTypeProjectionMachine(CandyBar())
val candyBarMachine: VendingTypeProjectionMachine<in CandyBar> = snackMachine

// Here it converts the return type to Any?(nullable any)
//val result: Any? = candyBarMachine.purchase(Coin())
```

```kt
// star projection
class VendingStarProjectionMachine<T : Product>(private val product: T) {
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Coin()
    fun performMaintenance() = println("All tuned up")
}

fun main() {

    val snackMachine: VendingStarProjectionMachine<Snack> = VendingStarProjectionMachine(Snack())
    val candyBarMachine: VendingStarProjectionMachine<CandyBar> = VendingStarProjectionMachine(CandyBar())

    var anyMachine: VendingStarProjectionMachine<*>
    anyMachine = snackMachine
    anyMachine = candyBarMachine

    anyMachine.performMaintenance()
}
```

### SubTyping

```txt
SuperType
+ exec(x: Int): Any
   ^
   |
SubType
+ exec(x: Any): String

1. The subtype must have each of the non private functions that exists in its supertype
2. The function parameter types in the subtype must be the same as or more general than the corresponding parameter types in its supertype - Contravariance
3. The function return types in the subtype must be the same as or more specific than the corresponding return types in its supertype - Covariance
```

### Reified

- When you create an inline function with the type parameter reified this code won't loose type arguments at the
  runtime(type erasure)
- Inline function can be called from java, but inline function with reified type parameter cannot be called from java

```kt
inline fun <reified T> secondItemHasType(list: List<*>): Boolean {
    return list[1] is T
}

fun main() {
    val list: List<Any> = listOf(1, "two", 3)
    val secondItemIsString = secondItemHasType<String>(list)
    println(secondItemIsString)
}
```

### Decorator Pattern

```kt
package advanced_concepts.patterns.decorator

import java.time.Clock
import java.util.*

//Component
fun interface Logger {
    fun log(message: String)
}

//Concrete Component
class ConsoleLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}

//Concrete Decorator
class UniqueIdLogger(private val logger: Logger) : Logger {
    override fun log(message: String) = logger.log("${UUID.randomUUID()} $message")
}

class ThreadNameLogger(private val logger: Logger) : Logger {
    override fun log(message: String) = logger.log("$message (on ${Thread.currentThread().name} thread)")
}

class DateTimeLogger(private val logger: Logger, private val clock: Clock = Clock.systemUTC()) : Logger {
    override fun log(message: String) = logger.log("[${clock.instant()}] $message")
}

fun main() {
    val consoleLogger = UniqueIdLogger(ThreadNameLogger(DateTimeLogger(ConsoleLogger())))
    consoleLogger.log("Application initialized")
}
```

```kt
import java.time.Clock
import java.util.*

fun interface Logger {
    fun log(message: String)
}

val consoleLogger = Logger { println(it) }
fun Logger.withUniqueId() = Logger { log("${UUID.randomUUID()} $it") }
fun Logger.withThreadName() = Logger { log("$it (on ${Thread.currentThread().name} thread)") }
fun Logger.withDateTime(clock: Clock = Clock.systemUTC()) = Logger { log("[${clock.instant()}] $it") }

fun main() {
    val consoleLogger = consoleLogger.withDateTime().withThreadName().withUniqueId()
    consoleLogger.log("Application initialized")
}
```

### Strategy Pattern

```kt
//Strategy
fun interface Validator {
    fun isValid(value: String): Boolean
}

//Concrete Strategy
class EmailValidator : Validator {
    override fun isValid(value: String) = value.contains("@") && value.contains(".")
}

class UsernameValidator : Validator {
    override fun isValid(value: String) = value.isNotEmpty()
}

class PasswordValidator : Validator {
    override fun isValid(value: String) = value.length >= 8
}

//Context
class FormField(val name: String, val value: String, private val validator: Validator) {
    fun isValid(): Boolean {
        return validator.isValid(value)
    }
}
```

```kt
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
```

### State Pattern

```kt
package advanced_concepts.patterns.state

enum class UserState {
    ANONYMOUS, UNVERIFIED, AUTHENTICATED
}

class User(var email: String? = null, var state: UserState = UserState.ANONYMOUS) {

    fun signUp(email: String) {
        when (state) {
            UserState.ANONYMOUS -> {
                println("Signing up with email: $email")
                this.email = email
                state = UserState.UNVERIFIED
            }

            UserState.UNVERIFIED -> println("You are already signed up")
            UserState.AUTHENTICATED -> println("You are already signed up and authenticated")
        }
    }

    fun verifyEmail(token: String) {
        when (state) {
            UserState.ANONYMOUS -> println("You must sign up before verifying your email")
            UserState.UNVERIFIED -> {
                println("Verifying email with token: $token")
                state = UserState.AUTHENTICATED
            }

            UserState.AUTHENTICATED -> println("You are already verified")
        }
    }

    fun viewContent() {
        when (state) {
            UserState.ANONYMOUS -> println("Viewing public content")
            UserState.UNVERIFIED -> println("Viewing personalized content for unverified account")
            UserState.AUTHENTICATED -> println("Viewing personalized content")
        }
    }

    fun viewProfile() {
        when (state) {
            UserState.ANONYMOUS -> println("You must sign in to view your profile")
            UserState.UNVERIFIED -> println("Profile: $email (Unverified account, please verify your email)")
            UserState.AUTHENTICATED -> println("Profile: $email (Fully authenticated)")
        }
    }
}
```

```kt
package advanced_concepts.patterns.state

//State
interface UserState {
    fun signUp(user: User, email: String)
    fun verifyEmail(user: User, token: String)
    fun viewContent()
    fun viewProfile(user: User)
    fun editProfile(user: User, newEmail: String)
}

//Concrete State
object Anonymous : UserState {
    override fun signUp(user: User, email: String) {
        println("Signing up with email: $email")
        user.email = email
        user.state = Unverified
    }

    override fun verifyEmail(user: User, token: String) = println("You must sign up before verifying your email")
    override fun viewContent() = println("Viewing public content")
    override fun viewProfile(user: User) = println("You must sign in to view your profile")
    override fun editProfile(user: User, newEmail: String) = println("You must sign in to edit your profile")

}

object Unverified : UserState {
    override fun signUp(user: User, email: String) = println("You are already signed up")
    override fun verifyEmail(user: User, token: String) {
        println("Verifying email with token: $token")
        user.state = Authenticated
    }

    override fun viewContent() = println("Viewing personalized content for unverified account")
    override fun viewProfile(user: User) =
        println("Profile: ${user.email} (Unverified account, please verify your email)")

    override fun editProfile(user: User, newEmail: String) =
        println("Please verify your account before editing your profile")

}

object Authenticated : UserState {
    override fun signUp(user: User, email: String) = println("You are already signed up and authenticated")
    override fun verifyEmail(user: User, token: String) = println("You are already verified")
    override fun viewContent() = println("Viewing personalized content")
    override fun viewProfile(user: User) = println("Profile: ${user.email} (Fully authenticated)")
    override fun editProfile(user: User, newEmail: String) {
        println("Profile: Updating email from ${user.email} to $newEmail")
        user.email = newEmail
    }
}

// Context
class User(var email: String? = null, var state: UserState = Anonymous) {
    fun signUp(email: String) = state.signUp(this, email)
    fun verifyEmail(token: String) = state.verifyEmail(this, token)
    fun viewContent() = state.viewContent()
    fun viewProfile() = state.viewProfile(this)
    fun editProfile(newEmail: String) = state.editProfile(this, newEmail)
}
```

### Scopes

- Function bodies and lambdas have statement scope
- Class bodies and the file itself have declaration scope

```kt
// Top level file scope
val pi = 3.14

// Top level file scope
class Circle(var radius: Double) {
    // Class body scope
    val diameter = radius * 2

    fun circumference(): Double {
        // Function body scope
        return pi * diameter
    }
}

fun createCircles(radii: List<Double>): List<Circle> {
    // Function body scope
    return radii.map { radius ->
        // Lambda scope
        Circle(radius)
    }
}
```

```kt
package advanced_concepts.extension_functions

val pi = 3.14

fun main() {
    val radii = listOf(1.0, 2.0, 3.0)

    class Circle(
        val radius: Double
    ) {
        fun circumference(): Double {
            val multiplier = 2.0
            //TODO: What are the variables visible here
            val diameter = radius * multiplier
            return multiplier * pi * radius
        }

        val area = pi * radius * radius
    }

    val areas = radii.map {
        Circle(it).area
    }
}
```

- The variables that are visible there are?
    - multiplier - Going up in statement scope
    - area - Declaration scope of the class
    - radius - Parameter scope of the class
    - radii - Going up in statement scope
    - pi - Declaration scope of the file

### Scope Functions

###### with()

- We can use the with() scope function to introduce a new scope in which the context object is represented as
  an implicit receiver

```kt
address.street1 = "9801 Maple Ave"
address.street2 = "Apartment 255"
address.city = "Rocksteady"
address.state = "IN"
address.postalCode = "12345"

// Here the address is the context object
with(address) {
    // Below is the lambda
    street1 = "9801 Maple Ave"
    street2 = "Apartment 255"
    city = "Rocksteady"
    state = "IN"
    postalCode = "12345"
}
```

###### run()

- The run() function works the same as with(), but it’s an extension function instead of a normal, top-level
  function

```kt
// address is the context object
address.run {
    street1 = "9801 Maple Ave"
    street2 = "Apartment 255"
    city = "Rocksteady"
    state = "IN"
    postalCode = "12345"
}
```

###### let()

- Might be the most frequently-used scope function. It’s very similar to run(),but instead of representing the
  context object as an implicit receiver, it’s represented as the parameter of its lambda
- run(), let() and with() functions returns the result of the lambda

```kt
address.let { titleWithoutPrefix -> "'$titleWithoutPrefix'" }

address.let { "'$it'" }
```

###### also()

- Function represents the context object as the lambda parameter
- Returns the context object as well

```kt
val title = "The Robots from Planet X3"
val newTitle = title
    .removePrefix("The ")
    .also {::println}
    .singleQuoted()
    .uppercase()
```

###### apply()

- Function returns the context object rather than the result, but it also represents the context object as the implicit receiver

```kt
val title = "The Robots from Planet X3"
val newTitle = title
    .removePrefix("The ")
    .apply { println(this) }
    .singleQuoted()
    .uppercase()
```

|                         | Context object is implicit receiver(this) | Context object is lambda parameter(it) |
|-------------------------|----------------------------------------|-------------------------------------|
| Returns result of lambda | obj.run { }                            | obj.let { }                         |
| Returns context object  | obj.apply { }                          | obj.also { }                        |

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

fun main(){
    val favourites = FavouriteStrings("one", "two")
    for(favourite in favourites){
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

fun main(){
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
@JvmInline value class Id(val value: Int)
@JvmInline value class Title(val value: String)
@JvmInline value class Author(val value: String)

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
annotation class RequiresRoles(val role:String)
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

fun onScreenLoaded(user:User){
  if(user.isAuthenticated()) user.greet() else user.promptToSignIn()
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
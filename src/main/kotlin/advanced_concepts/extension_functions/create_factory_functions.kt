package advanced_concepts.extension_functions

class User(val name: String) {
    companion object
}

class UserRecord(val firstName: String, val lastName: String)

fun User.Companion.fromRecord(record: UserRecord) = User("${record.firstName} ${record.lastName}")

fun main() {
    // To do this you need to have a companion object on the receiver
    val user = User.fromRecord(UserRecord("heshan", "karu"))
}
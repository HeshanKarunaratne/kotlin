package kotlin_classes

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
package kotlin_classes

data class Book(val id: Int, val title: String, val author: String)

fun main(){
    val myBook = Book(256, "The Malt Shop Caper", "Slim Chancery")
    println(myBook)
    val myBookV2 = myBook.copy(author = "Heshan Karunaratne")
    println(myBookV2)
}
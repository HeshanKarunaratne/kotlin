package kotlin_classes

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
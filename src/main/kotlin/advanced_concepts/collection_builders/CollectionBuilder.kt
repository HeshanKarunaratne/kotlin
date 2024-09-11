package advanced_concepts.collection_builders

data class Content(val title: String, val pageNumber: Int)

// This function generate 5 lists
fun tableOfContentsV1(
    bookTitle: String, contents: List<Content>, copyright: String?
): List<String> = listOf(
    bookTitle.uppercase(), "Table of contents", "-----------------------"
) + contents.mapIndexed { num, c -> "${num + 1}. ${c.title} - ${c.pageNumber}" } + "-------------------" + if (copyright !== null) {
    listOf("", "Copyright $copyright")
} else {
    emptyList()
}


// This function generate only 1 mutable list
fun tableOfContentsV2(
    bookTitle: String, contents: List<Content>, copyright: String?
): MutableList<String> {
    val result = mutableListOf<String>()
    result.add(bookTitle.uppercase())
    result.add("Table of contents")
    result.add("-----------------------")

    contents.mapIndexedTo(result) { num, c -> "${num + 1}. ${c.title} - ${c.pageNumber}" }
    result.add("-----------------------")
    if (copyright !== null) {
        result.add("")
        result.add("Copyright $copyright")
    }
    return result
}


// This function uses Collection Builders buildList - It gives you the ability to create mutable lists but after creation stops from updating it
fun tableOfContentsV3(
    bookTitle: String, contents: List<Content>, copyright: String?
): List<String> = buildList {
    add(bookTitle.uppercase())
    add("Table of contents")
    add("-----------------------")
    contents.mapIndexedTo(this) { num, c -> "${num + 1}. ${c.title} - ${c.pageNumber}" }
    add("-----------------------")
    if (copyright !== null) {
        add("")
        add("Copyright $copyright")
    }
}

fun main() {
    val title = "Title"
    val contents = listOf(
        Content("ct1", 10),
        Content("ct2", 20),
        Content("ct3", 30),
    )
}
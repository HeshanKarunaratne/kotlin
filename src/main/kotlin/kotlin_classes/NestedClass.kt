package kotlin_classes

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
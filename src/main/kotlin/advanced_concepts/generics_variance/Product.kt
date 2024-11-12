package advanced_concepts.generics_variance

open class Product

open class Snack : Product() {
    fun getRandomSnack(): Snack {
        return Snack()
    }
}

class TrailMix : Snack()
class GummyBears : Snack()
class CandyBar : Snack()

open class Toy : Product()

class ActionFigure : Toy()
class BouncyBall : Toy()
class Sticker : Toy()
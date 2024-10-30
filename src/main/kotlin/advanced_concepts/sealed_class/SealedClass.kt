package advanced_concepts.sealed_class

enum class Size { CUP, BUCKET, BAG }

sealed class Request {
    val id: Int = kotlin.random.Random.nextInt()
}

class OrderRequest(val size: Size) : Request()
class RefundRequest(val size: Size, val reason: String) : Request()
class SupportRequest(val text: String) : Request()

object FrontDesk {
    fun receive(request: Request) {
        println("Handling request #${request.id}")
        when (request) {
            is OrderRequest -> IceCubeFactory.fulfillOrder(request)
            is RefundRequest -> IceCubeFactory.fulfillRefund(request)
            is SupportRequest -> HelpDesk.handle(request)
        }
    }
}

object IceCubeFactory {
    fun fulfillOrder(order: OrderRequest) = println("Fulfilling order #${order.id}")
    fun fulfillRefund(refund: RefundRequest) = println("Fulfilling refund #${refund.id}")
}

object HelpDesk {
    fun handle(request: SupportRequest) = println("Help desk is handling ${request.id}")
}

fun main() {
    val order = OrderRequest(Size.CUP)
    FrontDesk.receive(order)

    val refund = RefundRequest(Size.CUP, "Too small")
    FrontDesk.receive(refund)

    val support = SupportRequest("I can't open the bag of ice!")
    FrontDesk.receive(support)
}
package advanced_concepts.generics_variance

open class Money

open class Coin : Money()

class Nickel : Coin()
class Dime : Coin()
class Quarter : Coin()

open class Bill : Money()

class OneDollar : Bill()
class FiveDollar : Bill()
class TenDollar : Bill()
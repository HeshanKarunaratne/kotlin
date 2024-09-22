package advanced_concepts.generics_variance

interface VendingGenMachine<in T, out R>
class Test1 : VendingGenMachine<Coin, Snack>
class Test2 : VendingGenMachine<Money, CandyBar>
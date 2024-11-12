package advanced_concepts.generics

interface VendingGenMachine<in T, out R>
class Test1 : VendingGenMachine<Coin, Snack>
class Test2 : VendingGenMachine<Money, CandyBar>
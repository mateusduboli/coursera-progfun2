package week4.observer

trait Subscriber {
  def handler(publisher: Publisher): Unit
}

trait Publisher {
  private var subscribers: Set[Subscriber] = Set()

  def subscribe(subscriber: Subscriber): Unit =
    subscribers += subscriber

  def unsubscribe(subscriber: Subscriber): Unit =
    subscribers -= subscriber

  def publish(): Unit =
    subscribers.foreach(_.handler(this))
}

class BankAccount extends Publisher {
  private var balance = 0

  def currentBalance = balance

  def deposit(amount: Int): Unit = {
    if (amount >= 0) {
      balance += amount
      publish()
    }
  }

  def withdraw(amount: Int): Unit = {
    if (0 < amount && amount <= balance) {
      balance -= amount
    } else throw new Error("insuffiecient funds")
  }
}

class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))

  private var total: Int = _
  compute()

  private def compute() = total = observed.map(_.currentBalance).sum

  def handler(publisher: Publisher): Unit = compute()

  def totalBalance = total

}
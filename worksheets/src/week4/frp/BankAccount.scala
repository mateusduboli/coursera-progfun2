package week4.frp

class BankAccount {

  private val balance = Var(0)

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      balance += amount
    }
  }

  def withdraw(amount: Int): Unit = {
    if (0 < amount && amount <= balance) {
      balance -= amount
    } else throw new Error("insufficient funds")
  }
}

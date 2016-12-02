import week4.observer.{BankAccount, Consolidator}

val a = new BankAccount
val b = new BankAccount

val obs = new Consolidator(List(a, b))
obs.totalBalance

a.deposit(10)

obs.totalBalance

b.deposit(30)

obs.totalBalance
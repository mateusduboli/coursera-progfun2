package week4.frp

class Signal[T](expr: => T) {
  def apply(): T = ???
}

object NoSignal extends Signal[Nothing](???) {

}

object Signal {
  private val caller = new StackableVariable(NoSignal)
  def apply[T](expr: => T): Signal[T] = new Signal(expr)
}

class Var[T](expr: => T) extends Signal[T](expr) {
  override def apply(): T = ???
}

object Var {
  def apply[T](expr: => T): Var[T] = new Var(expr)
}

class StackableVariable[T](init: Signal[T]) {
  private var callers: List[Signal[T]] = List(init)

  def caller: Signal[T] = callers.head

  def withValue(newCaller: Signal[T])(op: => T): T = {
    callers = newCaller :: callers
    try op finally callers = callers.tail
  }

}



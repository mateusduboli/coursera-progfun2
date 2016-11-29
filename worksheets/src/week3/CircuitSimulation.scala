package week3

object Delays {
  val InverterDelay = 1
  val AndGateDelay = 1
  val OrGateDelay = 1
}

object CircuitSimulation extends Simulation {

  import Delays._

  type Action = () => Unit

  class Wire {
    private var sigVal = false
    private var actions: List[Action] = List()

    def getSignal: Boolean = sigVal

    def setSignal(s: Boolean): Unit = {
      if (s != sigVal) {
        sigVal = s
        actions foreach (_.apply)
      }
    }

    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction(): Unit = {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) { output setSignal !inputSig }
    }
    input addAction invertAction
  }

  def andGate(in1: Wire, in2: Wire, output: Wire): Unit = {
    def andAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(AndGateDelay) { output setSignal (in1Sig & in2Sig) }
    }
    in1 addAction andAction
    in2 addAction andAction
  }

  def orGate(in1: Wire, in2: Wire, output: Wire): Unit = {
    def orAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(OrGateDelay) { output setSignal (in1Sig | in2Sig) }
    }
    in1 addAction orAction
    in2 addAction orAction
  }
}
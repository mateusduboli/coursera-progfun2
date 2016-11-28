def power(x: Double, exp: Int): Double = {
  var r = 1.0
  var i = exp
  WHILE (i > 0) { r = r* x; i = i - 1 }
  r
}

def WHILE(condition: => Boolean)(command: => Unit): Unit = {
  if (condition) {
    command
    WHILE(condition)(command)
  } else ()
}


final class RepeatLoop(command: => Unit) {
  def UNTIL(condition: => Boolean): Unit = {
    command
    if (condition) UNTIL(condition)
    else ()
  }
}

def REPEAT(command: => Unit): RepeatLoop = new RepeatLoop(command)

power(0.2, 9)
power(2, 5)
power(3, 12)

var i = 0

REPEAT {
  println(s"Repeating: $i")
  i += 1
} UNTIL (i < 3)
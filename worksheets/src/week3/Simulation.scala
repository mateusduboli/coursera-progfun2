package week3

trait Simulation {
  def currentTime: Int = ???
  def afterDelay(delay: Int)(block: => Unit): Unit = ???
  def run(): Unit = ???
}
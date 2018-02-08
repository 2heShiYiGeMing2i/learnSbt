trait Subject {

  import scala.language.reflectiveCalls

  type State

  type Observer = {
    def receiveUpdate(state: Any): Unit
  }

  private var observers: List[Observer] = Nil

  def addObserver(ob: Observer) = observers ::= ob

  def notifyObserver(state: State): Unit = {
    observers foreach (_.receiveUpdate(state))
  }
}

object observer {

  def receiveUpdate(state: Any): Unit = println(s"got one! :$state")
}

val subject = new Subject {
  type State = Int
  protected var count = 0

  def increment(): Unit = {
    count += 1
    notifyObserver(count)
  }
}

subject.increment()
subject.increment()
subject.addObserver(observer)
subject.increment()
subject.increment()



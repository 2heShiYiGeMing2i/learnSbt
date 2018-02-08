abstract class SubjectObserver {
  type S <: Subject
  type O <: Observer

  trait Subject {
    private var observers = List[O]()

    def addObserver(observer: O) = observers ::= observer

    //    def notifyObserver() = observers.foreach(s => s.receiveUpdate(this))  这行不能通过编译
  }

  trait Observer {
    def receiveUpdate(subject: S)
  }

}
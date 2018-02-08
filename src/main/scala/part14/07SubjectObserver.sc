abstract class SubjectObserver {
  type S <: Subject
  type O <: Observer

  trait Subject {

    sdsd: S => // 自类型标记为self  意味着 subject 为子类型s的实例 s 类型 混入了subject特征

    private var observers = List[O]()

    def addObserver(observer: O) = observers ::= observer

    def notifyObserver() = observers.foreach(s => s.receiveUpdate(sdsd)) // 使用this之后就可以通过编译了

  }

  trait Observer {
    def receiveUpdate(subject: S)
  }

}
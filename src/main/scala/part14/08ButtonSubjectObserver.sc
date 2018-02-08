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

case class Button(label: String) { // ➊
  def click(): Unit = {}
}

object ButtonSubjectObserver extends SubjectObserver { //

  type S = ObservableButton
  type O = ButtonObserver

  class ObservableButton(label: String) extends Button(label) with Subject {
    override def click() = {
      super.click()
      notifyObserver()
    }
  }

  trait ButtonObserver extends Observer {
    def receiveUpdate(button: ObservableButton)
  }

}

import ButtonSubjectObserver._

class ButtonClickObserver extends ButtonObserver { // ➌
  val clicks = new scala.collection.mutable.HashMap[String, Int]()

  def receiveUpdate(button: ObservableButton) = {
    val count = clicks.getOrElse(button.label, 0) + 1
    clicks.update(button.label, count)
  }
}



val buttons = Vector(new ObservableButton("one"), new ObservableButton("three"))
val observer = new ButtonClickObserver
buttons foreach (_.addObserver(observer))
for (i <- 0 to 2) buttons(0).click()
for (i <- 0 to 4) buttons(1).click()
println(observer.clicks)
// Map("one" -> 3, "two" -> 5)

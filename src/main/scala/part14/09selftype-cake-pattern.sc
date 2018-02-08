trait Persistence {
  def startPersistence(): Unit /*= System.err.println("father data bases")*/
}

trait Midtier {
  def startMidtier(): Unit /*= System.err.println("father biz logic")*/

}

trait UI {
  def startUI(): Unit /*= System.err.println("father web ui")*/
}

trait Database extends Persistence {
  override def startPersistence(): Unit = {
    super.startPersistence()
    System.err.println("data bases")
  }
}

trait BizLogic extends Midtier {
  override def startMidtier(): Unit = System.err.println("biz logic")
}

trait WebUI extends UI {
  override def startUI(): Unit = System.err.println("web ui")
}

trait MyApp {
  self: Persistence with Midtier with UI =>
  // 蛋糕模式 没有用继承 就可以调用 基础类的所有方法 用自身类型 来粘合所有用到的方法

  def run(): Unit = {
    startUI()
    startMidtier()
    startPersistence()
  }
}

trait MyApp1 extends Persistence with Midtier with UI {
  def run = {

  }
}

object myApp extends MyApp with Database with BizLogic with WebUI


myApp.run()
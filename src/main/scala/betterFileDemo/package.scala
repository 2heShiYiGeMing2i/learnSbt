/**
  * Created by zhaolei on 2018/3/9
  */
package object betterFileDemo {
  implicit def toMethod(underData: UnderData): UnderTest = new UnderTest(underData)
}

package valid

import javax.validation.ConstraintViolation
import javax.validation.constraints.{Min, NotBlank}

import scala.annotation.meta.field

/**
  * Created by zhaolei on 2018/6/15
  */
class VTest(
    @(AtATest1 @field)
    val name: String,
    @(Min @field)(1)
    var age: Int = 0,
    @(NotBlank @field)(message = "密码不能为空")
    var password: String
) {
  override def toString = s"VTest($name, $age, $password)"
}

object VTest extends App {
  private val zl = new VTest("zl", 23, "23")
  System.err.println(zl.toString)
  private val zl1 = new VTest("zl1", 5, "3")
  System.err.println(zl1.toString)
  private val other = new VTest("other", 4, "4")
  System.err.println(other.toString)

  private val tests          = Seq(zl, zl1, other)
  private val validatorClazz = new ValidatorClazz

  import scala.collection.JavaConverters._

  private var err: Seq[ConstraintViolation[VTest]] = tests.flatMap(it => validatorClazz.validate(it).asScala)
  err.foreach(println)

  other.age = -3
  System.err.println(other.toString)
  err = tests.flatMap(it => validatorClazz.validate(it).asScala)
  err.foreach(println)
  //  @Valid
  //  val zl = new VTest()
  //  zl.age = -1
  //  System.err.println(zl.toString)
  //  private val zl1 = new VTest()
  //  System.err.println(zl1.toString)
  //  private val other = new VTest()
  //  System.err.println(other.toString)
  //  other.age = -2
  //  System.err.println(other.toString)
}

object e extends App {
  val max = 4
  (0 to max).foreach { _ =>
    print("*")
    (0 to max).foreach(_ => print(" *"))
    println(" *")
  }
}

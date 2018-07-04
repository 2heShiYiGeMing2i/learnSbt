package valid

import java.util

import javax.validation._
import javax.validation.{Validation, ValidatorFactory}

/**
  * Created by zhaolei on 2018/6/15
  */
class ValidatorClazz {


  val validatorFactory: ValidatorFactory = Validation.buildDefaultValidatorFactory
  val validator: Validator               = validatorFactory.getValidator

  def validate(it: VTest): util.Set[ConstraintViolation[VTest]] = {
    validator.validate(it)
  }

}

class AtATest1Validator extends ConstraintValidator[AtATest1, String] {

  override def initialize(it: AtATest1): Unit = super.initialize(it)

  override def isValid(value: String, context: ConstraintValidatorContext): Boolean = value.contains("zl")
}

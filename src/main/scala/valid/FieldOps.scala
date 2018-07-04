package valid

import java.lang.reflect.{Field, Modifier}

/**
  * Created by zhaolei on 2018/6/15
  */
case class FieldOps(underlying: Field) extends AnyVal {

  def ensureAccessible(): Field = {
    val field = underlying
    if ((!Modifier.isPublic(field.getModifiers) || !Modifier.isPublic(field.getDeclaringClass.getModifiers)
      || Modifier.isFinal(field.getModifiers)) && !field.isAccessible) {
      field.setAccessible(true)
    }
    field
  }

  def getValue[T](target: AnyRef): T = ensureAccessible().get(target).asInstanceOf[T]

  def setValue[T](target: AnyRef, value: Any): Unit = ensureAccessible().set(target, value)
}
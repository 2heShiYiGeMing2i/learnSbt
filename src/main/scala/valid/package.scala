import java.lang.reflect.Field

/**
  * Created by zhaolei on 2018/6/15
  */
package object valid {
  implicit def toFieldOps(field: Field): FieldOps = FieldOps(field)
}

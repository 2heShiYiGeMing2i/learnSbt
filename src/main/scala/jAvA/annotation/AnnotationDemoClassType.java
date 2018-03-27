package jAvA.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhaolei on 2018/3/10
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AnnotationDemoClassType {
}

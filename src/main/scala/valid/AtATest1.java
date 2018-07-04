package valid;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by zhaolei on 2018/6/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = AtATest1Validator.class)
public @interface AtATest1 {
    String message() default "测试 验证 注释 就是不行 怎么地吧";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package io.github.bw.libra.common.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被标注的方法和类只能在测试代码中出现
 *
 * @author zhangyunan
 */
@Retention(RetentionPolicy.CLASS)
@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.METHOD,
    ElementType.TYPE,
    ElementType.CONSTRUCTOR
})
@Documented
public @interface VisibleForTesting {

}

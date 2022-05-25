package io.github.bw.libra.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para indicar que o sistema deve ser reiniciado para que as alterações sejam aplicadas.
 * <p>
 * 必须重新启动系统才能使更改生效的配置。
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Documented
public @interface NeedReboot {

}

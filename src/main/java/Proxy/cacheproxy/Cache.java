package Proxy.cacheproxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Аннотация, которая указывает, что необходимо аннотированный метод кешировать
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheType cacheType() default CacheType.IN_MEMORY;
    String fileNamePrefix() default "";
    boolean zip() default false;
    byte[] identityBy() default {};
    int listSize() default -1;
}

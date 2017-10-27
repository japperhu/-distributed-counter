package com.hu.annotation;

import java.lang.annotation.*;

/**
 * 属性前缀标识
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface PropertiesPrefix {

     String value();
}

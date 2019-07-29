package com.rc.util.Impl;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CustomParamsFilter {
    String value() default "";
}

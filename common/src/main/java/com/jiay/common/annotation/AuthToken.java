package com.jiay.common.annotation;

import java.lang.annotation.*;

/**
 * 加上该注解的类http请求时需要进行token鉴权
 * @author jiay
 * @date 2020-03-18
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {
}

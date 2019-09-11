package com.daselearn.ssmjar.strategy.annotation;


import com.daselearn.ssmjar.strategy.enums.HandlerTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    HandlerTypeEnum value();
}

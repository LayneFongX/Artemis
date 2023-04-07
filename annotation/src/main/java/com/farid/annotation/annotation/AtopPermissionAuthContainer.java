package com.farid.annotation.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuthContainer {

    AtopPermissionAuth[] value();

}
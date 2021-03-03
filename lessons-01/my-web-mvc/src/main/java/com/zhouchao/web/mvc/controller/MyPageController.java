package com.zhouchao.web.mvc.controller;

import java.lang.annotation.*;

/**
 * @Author zhouchao
 * @Date 2021/3/2 15:27
 * @Description
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyPageController {

    String value();

    String httpMethod();
}

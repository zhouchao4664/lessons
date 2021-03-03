package com.zhouchao.web.mvc;

import com.zhouchao.web.mvc.controller.Controller;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author zhouchao
 * @Date 2021/3/2 16:47
 * @Description 处理方法信息类
 **/

public class HandlerRequestInfo {

    private final String requestPath;

    private final Method handlerMethod;

    private final Set<String> supportedHttpMethods;

    private final Controller controller;

    public HandlerRequestInfo(String requestPath, Method handlerMethod, Set<String> supportedHttpMethods, Controller controller) {
        this.requestPath = requestPath;
        this.handlerMethod = handlerMethod;
        this.supportedHttpMethods = supportedHttpMethods;
        this.controller = controller;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public Method getHandlerMethod() {
        return handlerMethod;
    }

    public Set<String> getSupportedHttpMethods() {
        return supportedHttpMethods;
    }

    public Controller getController() {
        return controller;
    }
}

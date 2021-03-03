package com.zhouchao.web.mvc;

import com.zhouchao.web.mvc.controller.Controller;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhouchao
 * @Date 2021/3/2 16:26
 * @Description
 **/

public class FrontControllerServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    @Override
    public void init(){
        initHandleMethods();
    }

    /**
     * 读取所有controller对应的类和方法
     */
    private void initHandleMethods() {

    }

}

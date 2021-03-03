package com.zhouchao.web.mvc;

import com.sun.org.apache.xpath.internal.functions.FuncSubstringAfter;
import com.zhouchao.web.mvc.controller.Controller;
import com.zhouchao.web.mvc.controller.MyPageController;
import com.zhouchao.web.mvc.controller.MyRestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.replace;
import static org.apache.commons.lang.StringUtils.substringAfter;

/**
 * @Author zhouchao
 * @Date 2021/3/2 16:26
 * @Description
 **/

public class FrontControllerServlet extends HttpServlet {

    /**
     * 请求路径和 {@link HandlerRequestInfo} 映射关系缓存
     */
    private Map<String, HandlerRequestInfo> handleRequestInfoMapping = new HashMap<>();

    @Override
    public void init() {
        initHandleMethods();
    }

    /**
     * 读取所有controller对应的类和方法
     */
    private void initHandleMethods() {
        for (Controller controller : ServiceLoader.load(Controller.class)) {
            Class<?> controllerClass = controller.getClass();
            Path pathFromClass = controllerClass.getAnnotation(Path.class);
            String requestPath = pathFromClass == null ? "" : pathFromClass.value();
            Method[] methods = controllerClass.getMethods();
            //循环遍历查找支持http请求的方法
            for (Method method : methods) {
                Set<String> supportedHttpMethods = findSupportedHttpMethods(method);
                Path pathFromMethod = method.getAnnotation(Path.class);
                if (pathFromMethod != null) {
                    requestPath += pathFromMethod.value();
                }
                handleRequestInfoMapping.put(requestPath, new HandlerRequestInfo(requestPath, method, supportedHttpMethods, controller));
            }
        }
    }

    private Set<String> findSupportedHttpMethods(Method method) {
        Set<String> supportedHttpMethods = new HashSet<>();
        for (Annotation annotation : method.getAnnotations()) {
            HttpMethod httpMethod = annotation.annotationType().getAnnotation(HttpMethod.class);
            if (httpMethod != null) {
                supportedHttpMethods.add(httpMethod.value());
            }
        }
        if (supportedHttpMethods.isEmpty()) {
            supportedHttpMethods.addAll(asList(HttpMethod.GET, HttpMethod.POST,
                    HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.HEAD, HttpMethod.OPTIONS));
        }
        return supportedHttpMethods;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String servletContextPath = req.getContextPath();
        String prefixPath = servletContextPath;
        String requestMappingPath = substringAfter(requestURI, replace(prefixPath, "//", "/"));
        HandlerRequestInfo handlerRequestInfo = handleRequestInfoMapping.get(requestMappingPath);
        if (handlerRequestInfo != null) {
            try {
                Controller controller = handlerRequestInfo.getController();
                String httpMethod = req.getMethod();
                if (!handlerRequestInfo.getSupportedHttpMethods().contains(httpMethod)) {
                    resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    return;
                }
                if (controller instanceof MyPageController) {
                    MyPageController myPageController = MyPageController.class.cast(controller);
                    String viewPath = myPageController.execute(req, resp);
                    if (!viewPath.startsWith("/")) {
                        viewPath = "/" + viewPath;
                    }
                    req.getServletContext().getRequestDispatcher(viewPath).forward(req, resp);
                    return;
                } else if (controller instanceof MyRestController) {
                    // @TODO
                }
            } catch (Throwable throwable) {
                if (throwable.getCause() instanceof IOException) {
                    throw (IOException) throwable.getCause();
                } else {
                    throw new ServletException(throwable.getCause());
                }
            }

        }
    }
}

package com.fc.interceptor;

import com.fc.utils.JwtUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class LogoutInterceptor implements HandlerInterceptor {

    /**
     * 后置拦截，退出登录方法执行完毕后执行
     * 通过反射机制修改JWT工具类中的盐值
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @param ex 异常
     * @throws Exception 抛出的异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("退出登录拦截器");

        // 获取session
        HttpSession session = request.getSession(false);

        Map<String, Object> claim = (Map<String, Object>) request.getAttribute("claim");

        String phone = (String) claim.get("phone");

        System.out.println("退出登录时Session获取的phone：" + phone);

        // 清楚Session
        session.removeAttribute(phone);
    }
}

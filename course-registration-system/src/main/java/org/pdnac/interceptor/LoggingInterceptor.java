package org.pdnac.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import java.time.LocalDateTime;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        String email = request.getParameter("email");  // useful for login
        String method = request.getMethod();

        if (uri.equals("/login") && method.equalsIgnoreCase("POST")) {
            System.out.println("[LOGIN ATTEMPT] " + email + " at " + LocalDateTime.now());
        }

        if (uri.startsWith("/register/") && method.equalsIgnoreCase("POST")) {
            System.out.println("[COURSE REGISTRATION] Accessed at " + LocalDateTime.now());
        }

        return true;
    }
}
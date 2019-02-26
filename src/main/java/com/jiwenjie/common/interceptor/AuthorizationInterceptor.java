//package com.jiwenjie.common.interceptor;
//
//import com.jiwenjie.common.Constant;
//import com.jiwenjie.common.auth.AuthPassport;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
///**
// * author: Jiwenjie
// * email: Jiwenjie97@gmail.com
// * time: 2018-12-13
// * desc:
// */
//public class AuthorizationInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
//        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
//            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
//
//            if (authPassport == null) {
//                System.out.println(httpServletRequest.getRequestURL().toString());
//                return true;
//            } else {
//                User user = (User) httpServletRequest.getSession().getAttribute(Constant.SESSION_USER);
//                if (user == null) {
//                    httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
//                    PrintWriter pw = httpServletResponse.getWriter();
//                    pw.write("{\"code\" : 403, \"message\" : \"Need Login\"}");
//                    pw.flush();
//                    pw.close();
//                    return false;
//                } else
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("AuthorizationInterceptor: postHandle -->");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("AuthorizationInterceptor: afterCompletion -->");
//    }
//}

package com.example.fitnessdb.web.interceptor;

import com.example.fitnessdb.service.ExceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

@Component
public class PageInterceptor implements HandlerInterceptor {

    private static final String LOGIN_URL = "http://localhost:8080/users/login";
 // Logger logger = LoggerFactory.getLogger("Intercept page time taken");
    private static Logger logger = LoggerFactory.getLogger(PageInterceptor.class);
    FileHandler fileHandler;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            fileHandler = new FileHandler("src/main/resources/output/event/interceptor_"
//                    + dateFormat.format(Calendar.getInstance().getTime()) + ".log");
//           // logger.addHandler(fileHandler);
//            SimpleFormatter formatter = new SimpleFormatter();
//           fileHandler.setFormatter(formatter);
//
//            if (request.getRequestURL().toString().equals(LOGIN_URL)) {
//                long startTime = System.currentTimeMillis();
//                logger.log(Level.INFO, String.format("Requested URL is %s starting time at %s",
//                        request.getRequestURL().toString(), System.currentTimeMillis()));
//                request.setAttribute("startTime", startTime);
//            }
////            if (request.getRequestURL().toString().equals(LOGIN_URL)) {
////                long startTime = System.currentTimeMillis();
////                logger.info(String.format("Requested URL is %s starting time at %s",
////                        request.getRequestURL().toString(), System.currentTimeMillis()));
////                request.setAttribute("startTime", startTime);
////            }
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//
////        if (request.getRequestURL().toString().equals(LOGIN_URL)) {
////            logger.log(Level.INFO, String.format("Requested URL is %s and post handler current time is %s",
////                    request.getRequestURL().toString(), System.currentTimeMillis()));
////        }
//
//
////            logger.info(String.format("Requested URL is %s and post handler current time is %s",
////                    request.getRequestURL().toString(), System.currentTimeMillis()));
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
//                                Object handler, Exception ex)
//            throws Exception {
//
//        if (request.getRequestURL().toString().equals(LOGIN_URL)) {
//            long startTime = (Long) request.getAttribute("startTime");
//            logger.log(Level.INFO, String.format("Requested URL is %s and after completion time is %s",
//                    request.getRequestURL().toString(), System.currentTimeMillis()));
//            logger.log(Level.INFO, String.format("Requested URL is %s and the time toked was %s ",
//                    request.getRequestURL().toString(), System.currentTimeMillis() - startTime));
//        }
//
////        if (request.getRequestURL().toString().equals(LOGIN_URL)) {
////            long startTime = (Long) request.getAttribute("startTime");
////            logger.info(String.format("Requested URL is %s and after completion time is %s",
////                    request.getRequestURL().toString(), System.currentTimeMillis()));
////            logger.info(String.format("Requested URL is %s and the time toked was %s ",
////                    request.getRequestURL().toString(), System.currentTimeMillis() - startTime));
////        }
//    }
}

package com.example.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getMessage());
        mav.addObject("url", req.getRequestURL());
        resp.setStatus(404);
        mav.setViewName("error/error");
        return mav;
    }
}

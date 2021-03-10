package com.xhh_study1.community.advice;

import com.alibaba.fastjson.JSON;
import com.xhh_study1.community.dto.ResultDTO;
import com.xhh_study1.community.exception.CustomizeErrorCode;
import com.xhh_study1.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response){

        String contentType=request.getContentType();
        if ("application/json".equals(contentType)){
            ResultDTO resultDTO;
            //返回json
            if (ex instanceof CustomizeException){
                resultDTO=  ResultDTO.errorOf((CustomizeException) ex);
            }else {
                resultDTO=  ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer=response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            return null;
        }else{
            //返回页面
            HttpStatus status=getStatus(request);
            if (ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }


    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode=(Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode==null){
            return  HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}

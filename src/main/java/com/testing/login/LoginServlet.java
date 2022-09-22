package com.testing.login;


import com.alibaba.fastjson.JSONObject;
import com.testing.model.Result;
import mysql.MysqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String loginName = request.getParameter("user");
        String password = request.getParameter("password");
        System.out.println("Username as a parameter is"+loginName);
        System.out.println("Password as a parameter is"+password);


        Pattern illW=Pattern.compile("[\\W\\-]");
        Matcher uMatcher=illW.matcher(loginName);
        Matcher pMatcher=illW.matcher(password);




        HttpSession nowSession = request.getSession();
        String sessionid = request.getSession().getId();
        System.out.println("sessionid is"+sessionid);

        Result result=new Result();

        if(nowSession.getAttribute("user") == null) {
            if (loginName != null && loginName.trim().length() > 0 && password != null) {

                if (!uMatcher.find() && !pMatcher.find()) {

                    MysqlUtils mu = new MysqlUtils();
                    mu.createConByProp();

                    if (mu.loginCheck(loginName, password)) {
                        result.setStatus("200");
                        result.setMsg("Congratulation！ You are logged in successfully");
                        nowSession.setAttribute("user", loginName);
                        nowSession.setMaxInactiveInterval(120);
                        Cookie jsessionId = new Cookie("JSESSIONID",sessionid);
                        jsessionId.setMaxAge(120);
                        response.addCookie(jsessionId);


                        //response.getWriter().append("Congratulation！ You are logged successfully");
                    } else {

                        result.setStatus("-1");
                        result.setMsg("User name or password is wrong！");
                        //response.getWriter().append("User name or passport is wrong！");
                    }
                } else {
                    result.setStatus("-2");
                    result.setMsg("Illegal characters entered, please check");
                    //response.getWriter().append("Illegal characters entered, please check");
                }
            } else {
                result.setStatus("-3");
                result.setMsg("Username and password cannot be empty");

                // response.getWriter().append("Username and password cannot be empty");
            }
        } else {
            result.setStatus("-4");
            result.setMsg("Username: "+nowSession.getAttribute("user")+" have already logged in, you cannot log in again!");

        }
        String resultJson = JSONObject.toJSONString(result);
        System.out.println("The resulting string is"+resultJson);
        response.getWriter().append(resultJson);




    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String loginName = request.getParameter("user");
        String password = request.getParameter("password");
        if("Roy".equals(loginName)&&"123456".equals(password)){
            response.getWriter().append("Congratulation！ You are logged in successfully");
        }else{
            response.getWriter().append("User name or password is wrong！");
        }


        response.getWriter().append("The first request to the Get interface successful!");
    }
}

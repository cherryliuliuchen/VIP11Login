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

/**
 * @Classname ${NAME}
 * @Description 类型说明
 * @Date 2021/12/29 21:56
 * @Created by 特斯汀Roy
 */
@WebServlet
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String loginName = request.getParameter("user");
        String password = request.getParameter("password");
        System.out.println("传参的用户名是"+loginName);
        System.out.println("传参的密码是"+password);


        Pattern illW=Pattern.compile("[\\W\\-]");
        Matcher uMatcher=illW.matcher(loginName);
        Matcher pMatcher=illW.matcher(password);




        HttpSession nowSession = request.getSession();
        String sessionid = request.getSession().getId();
        System.out.println("sessionid是"+sessionid);

        Result result=new Result();

        if(nowSession.getAttribute("user") == null) {
            if (loginName != null && loginName.trim().length() > 0 && password != null) {

                if (!uMatcher.find() && !pMatcher.find()) {

                    MysqlUtils mu = new MysqlUtils();
                    mu.createConByProp();

                    if (mu.loginCheck(loginName, password)) {
                        result.setStatus("200");
                        result.setMsg("恭喜您登录成功");
                        nowSession.setAttribute("user", loginName);
                        nowSession.setMaxInactiveInterval(120);
                        Cookie jsessionId = new Cookie("JSESSIONID",sessionid);
                        jsessionId.setMaxAge(120);
                        response.addCookie(jsessionId);


                        //response.getWriter().append("恭喜您登录成功");
                    } else {

                        result.setStatus("-1");
                        result.setMsg("用户名密码错误！");
                        //response.getWriter().append("用户名密码错误！");
                    }
                } else {
                    result.setStatus("-2");
                    result.setMsg("输入了非法字符，请检查");
                    //response.getWriter().append("输入了非法字符，请检查");
                }
            } else {
                result.setStatus("-3");
                result.setMsg("用户名密码不能为空");

                // response.getWriter().append("用户名密码不能为空");
            }
        } else {
            result.setStatus("-4");
            result.setMsg("已经有用户"+nowSession.getAttribute("user")+"登录过了，不能再次登录!");

        }
        String resultJson = JSONObject.toJSONString(result);
        System.out.println("结果字符串为"+resultJson);
        response.getWriter().append(resultJson);



        //response.getWriter().append("第一次请求post接口成功！");
    }

/*        if("Roy".equals(loginName)&&"123456".equals(password)){
            response.getWriter().append("恭喜您登录成功");
        }else{
            response.getWriter().append("用户名密码错误！");
        }
        response.getWriter().append("第一次请求post接口成功！");*/


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求中user和password参数。
        String loginName = request.getParameter("user");
        String password = request.getParameter("password");
        if("Roy".equals(loginName)&&"123456".equals(password)){
            response.getWriter().append("恭喜您登录成功");
        }else{
            response.getWriter().append("用户名密码错误！");
        }


        response.getWriter().append("第一次请求Get接口成功！");
    }
}

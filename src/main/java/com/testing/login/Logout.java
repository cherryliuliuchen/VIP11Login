package com.testing.login;

import com.alibaba.fastjson.JSONObject;
import com.testing.model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        System.out.println("登出接口中的sessionid是"+session.getId());
        Result result=new Result();
        //判断是否有用户登录
        if(session.getAttribute("user")==null){
            result.setStatus("-1");
            result.setMsg("当前没有用户登录");
        }else{
            result.setStatus("0");
            result.setMsg("当前登录的用户是"+session.getAttribute("user")+"已为您退出登录");
        }
        //不管有没有用户登录，都重置一下session。
        session.invalidate();
        String resultJson = JSONObject.toJSONString(result);
        response.getWriter().append(resultJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

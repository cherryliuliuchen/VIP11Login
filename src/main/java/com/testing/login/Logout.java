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
        System.out.println("The sessionid in the logout interface is"+session.getId());
        Result result=new Result();

        if(session.getAttribute("user")==null){
            result.setStatus("-1");
            result.setMsg("No user is currently logged in");
        }else{
            result.setStatus("0");
            result.setMsg("The currently logged in user is "+session.getAttribute("user")+" Signed out for you");
        }
        //reset session
        session.invalidate();
        String resultJson = JSONObject.toJSONString(result);
        response.getWriter().append(resultJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package com.testing.login;

import com.alibaba.fastjson.JSONObject;
import com.testing.model.Result;
import mysql.MysqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet
public class GetUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        Result result = new Result();
        if(session.getAttribute("user")==null){
            result.setStatus("-1");
            result.setMsg("必须登录之后才能查询用户信息");

        }else{
            String user=session.getAttribute("user").toString();
            String sql="select * from userinfo where username='"+user+"'";
            MysqlUtils mu=new MysqlUtils();
            mu.createConByProp();
            List<Map<String, String>> userinfoList = mu.queryDatas(sql);
            result.setStatus("0");
            result.setMsg("查询用户信息成功");
            result.setData(userinfoList.get(0));


        }
        String resultJson = JSONObject.toJSONString(result);
        response.getWriter().append(resultJson);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package mysql;

import javax.xml.transform.Result;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class MysqlUtils {

    Connection mycon;

    public void createConnection(String mysqlIp, String mysqluser,String mysqlpwd){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            mycon = DriverManager.getConnection(
                    "jdbc:mysql://"+mysqlIp+":3306/test_project?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                    mysqluser,mysqlpwd);


        } catch(Exception e){
            e.printStackTrace();
            System.out.println("连接数据库失败");

        }

    }

    public void createConByProp(){
        try {
            InputStream resourceAsStream = MysqlUtils.class.getResourceAsStream("/mysql.properties");
            Properties mysqlprop=new Properties();
            mysqlprop.load(resourceAsStream);
            String mysqlurl = mysqlprop.getProperty("mysqlurl");
            String mysqluser = mysqlprop.getProperty("mysqluser");
            String mysqlpwd = mysqlprop.getProperty("mysqlpwd");
            Class.forName("com.mysql.jdbc.Driver");
            mycon=DriverManager.getConnection(mysqlurl,mysqluser,mysqlpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public boolean loginCheck(String user, String pwd) {
        try {
            Statement statement = mycon.createStatement();
            String sql = "select * from userinfo where username='chen' and `password`='880705'";
            sql = sql.replace("chen", user).replace("880705",pwd);
            System.out.println("执行的sql语句是"+sql);
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet!=null&&resultSet.next()){
                //可加
                statement.close();
                resultSet.close();
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  false;
        }

    }

    public List<Map<String,String>> queryDatas(String sql){
        try {
            Statement statement = mycon.createStatement();
            System.out.println("执行的sql语句是"+sql);
            ResultSet resultSet = statement.executeQuery(sql);
            //获取查询结果元信息，也就是列名等。
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<Map<String,String>> allDatas=new ArrayList<>();
            while (resultSet!=null&&resultSet.next()){
                //循环每个列名，将对应的值存到map里
                Map<String,String> lineData=new HashMap<>();
                for(int index=1;index<=metaData.getColumnCount();index++){
                    String columnName = metaData.getColumnName(index);
                    lineData.put(columnName,resultSet.getString(columnName));
                }
                allDatas.add(lineData);
            }
            return allDatas;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }


}

package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlTest {

    public static void main(String[] args) {
        MysqlUtils mu=new MysqlUtils();
        mu.createConByProp();
        System.out.println(mu.queryDatas("select * from userinfo where username='NingWwei'  and `password`='94018'"));


    }
    public static void main1(String[] args) throws Exception {



        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test_project?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                "root","roy123456");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from userinfo where username='NingWwei' and password='123456'");
        if(resultSet.next()){
            System.out.println("Nick name is "+resultSet.getString("nickname"));
            System.out.println("User name is"+resultSet.getString(3));
            System.out.println("Login successfully");

        }else{
            System.out.println("Login failed");
        }


    }
}

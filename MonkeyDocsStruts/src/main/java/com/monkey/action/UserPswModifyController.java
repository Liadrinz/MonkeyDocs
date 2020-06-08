package com.monkey.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Map;
public class UserPswModifyController extends ActionSupport {
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response= ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Expose-Headers","responsemsg,token");
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        Gson gson=new Gson();
        Map<String ,Object> map = gson.fromJson(responseStrBuilder.toString(),Map.class);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://106.54.101.125:3306/MonkeyDocDB","root","monkeydoc123");
        Statement st= conn.createStatement();
        String tel= (String) map.get("tel");
        String email= (String) map.get("email");
        String password= (String) map.get("password");
        String newpassword= (String) map.get("newpassword");
        String account;
        String keyword;
        if(tel=="")
        {
            account=email;
            keyword="email";
        }
        else {
            account=tel;
            keyword="tel";
        }
        ResultSet res= st.executeQuery("select * from User where "+keyword+"="+account);
        if(res.next()){
            String userpsw=res.getString(5);
            String userid=res.getString(1);
            if(userpsw.equals(password)){
                String sql1="UPDATE User SET password=? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql1);
                ps.setString(1, newpassword);
                ps.setString(2, userid);
                ps.execute();
                response.setHeader("responsemsg","psw_reset_success");
            }
            else {
                response.setHeader("responsemsg","psw_wrong");
            }
        }
        else{
            response.setHeader("responsemsg","user_does_not_exist");
        }
        return NONE;
    }
}

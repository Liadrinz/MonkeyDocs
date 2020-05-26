package com.monkey.interceptors;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.RestWorkflowInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class UserRequestInterceptor extends MethodFilterInterceptor {
    @Override
    public String doIntercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response= ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
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
        String method = request.getMethod();
        if(map==null){
            if(method.equals("GET")){
                response.setHeader("requestmsg","accepted");
                return invocation.invoke();
            }
            else
                return  null;
        }
        if(map.containsKey("mdId")){
            System.out.println("abc");
            String mdId= (String) map.get("mdId");
            String userId= (String) map.get("userId");
            String sql1="select role from DocumentMeta_has_User where userId=";
            String sql2=" and mdId=";
            ResultSet re=st.executeQuery(sql1+userId+sql2+mdId);
            if(!re.next()) {
                response.setHeader("responsemag","no_permission");
                return null;
            }
            String role=re.getString(1);
            if(role.equals("creator")){
                response.setHeader("responsemag","accepted");
                return invocation.invoke();
            }
            else if(role.equals("collaborator")){
                if(method.equals("GET")||method.equals("DELETE")){
                    response.setHeader("responsemag","no_permission");
                    return null;
                }
                else{
                    response.setHeader("responsemag","accepted");
                    return invocation.invoke();
                }
            }
        }
        else
        {
            String userId= (String) map.get("userId");
            String sql1="select * from User where id=";
            ResultSet re=st.executeQuery(sql1+userId);
            if(!re.next())
                return null;
            else
                return  null;
        }
        return null;
    }
}

package com.monkey.interceptors;

import com.google.gson.Gson;
import com.monkey.dao.UserDAO;
import com.monkey.token.TokenProccessor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Enumeration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class UserLoginDetector extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response= ServletActionContext.getResponse();
        String method = request.getMethod();
        System.out.println(method);
        if(method.equals("OPTIONS")){
            return actionInvocation.invoke();
        }
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Expose-Headers","responsemsg,token");
        response.setCharacterEncoding("UTF-8");
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        Enumeration<String> reqHeadInfos = request.getHeaderNames();
        String inputStr;
        String usrtoken = request.getHeader("token");
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        Gson gson=new Gson();
        Map<String ,Object> map = gson.fromJson(responseStrBuilder.toString(),Map.class);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://106.54.101.125:3306/MonkeyDocDB","root","monkeydoc123");
        Statement st= conn.createStatement();
        if(usrtoken==null||usrtoken.equals("")) {
        if(!map.containsKey("token")) {
            String tel = (String) map.get("tel");
            String sql1 = "select * from User where tel=";
            ResultSet re = st.executeQuery(sql1 + tel);
            if (!re.next()) {
                response.setHeader("responsemsg", "User_does_not_exists");
                return null;
            } else {
                String psw= (String) map.get("password");
                int userid=re.getInt(1);
                String psww = re.getString(5);
                String token = TokenProccessor.getInstance().makeToken();
                if(psw.equals(psww)){
                    Statement st2= conn.createStatement();
                    String sql2="select * from Usertoken where userid=";
                    ResultSet re2=st2.executeQuery(sql2+userid);
                    if(!re2.next()){
                        String sql3="INSERT INTO Usertoken (token,userid) VALUES (?,?)";
                        PreparedStatement ps = conn.prepareStatement(sql3);
                        ps.setString(1, token);
                        ps.setInt(2, userid);
                        ps.execute();
                        response.setHeader("responsemsg", token);
                        response.setHeader("userid",String.valueOf(userid));
                    }
                    else {
                        String sql3="UPDATE Usertoken SET token=? WHERE userid=?";
                        PreparedStatement ps= conn.prepareStatement(sql3);;
                        ps.setString(1,token);
                        ps.setInt(2,userid);
                        ps.execute();
                        response.setHeader("responsemsg", token);
                        response.setHeader("userid",String.valueOf(userid));
                    }
                }

                String psww = re.getString(5);
                String token = TokenProccessor.getInstance().makeToken();
                if(psw.equals(psww))
                    response.setHeader("responsemsg", token);
                else
                    response.setHeader("responsemsg","usr_name_or_psw_wrong");
                return actionInvocation.invoke();
            }
        }
        else{
            String token = usrtoken;
            String sql1 = "select * from Usertoken where token=";
            Statement st2= conn.createStatement();
            ResultSet re2 = st2.executeQuery(sql1 +"\""+ token+"\"");
            if(re2.next()){
                String tokenn=re2.getString(2);
                int userid=re2.getInt(3);
                Timestamp timestamp2=re2.getTimestamp(4);
                if(tokenn.equals(token)) {
                    Date date = new Date(System.currentTimeMillis());
                    Timestamp timestamp = new Timestamp(date.getTime());
                    float m=(timestamp.getTime()-timestamp2.getTime()+13*1000*60*60)/1000/60;
                    if(m>120){
                        response.setHeader("responsemsg", "token_expired");
                        return null;
                    }
                    else {
                        response.setHeader("responsemsg", "login_succeed");
                        response.setHeader("userid",String.valueOf(userid));
                        return actionInvocation.invoke();
                    }
                }
            String token = (String) map.get("token");
            String tel =(String) map.get("tel");
            String sql2="select id from User where tel=";
            String sql1 = "select * from tokenlist where token=";
            ResultSet re = st.executeQuery(sql1 + token);
            ResultSet re2 = st.executeQuery(sql2 + tel);
            if(re.next()){
                re2.next();
                String userid=re2.getString(1);
                String usrid=re.getString(3);
                String tokenn=re.getString(2);
                if(usrid.equals(userid)&&tokenn.equals(token))
                    return  actionInvocation.invoke();
                else return null;
            }
            else return null;
        }
    }
}

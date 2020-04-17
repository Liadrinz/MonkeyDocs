package com.monkey.action;

import com.monkey.entity.User;
import com.monkey.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Results({
        @Result(name = "success", type = "redirectAction", params = {"actionName", "user"}),
        @Result(name = "created", type = "redirectAction", params = {"namespace", "/user", "actionName", "${id}"})
})
public class UserController extends ActionSupport implements ModelDriven<Object>
{
    private int id;
    private User model = new User();
    private List<User> list;
    @Autowired
    private UserService userService;
    public void setId(String id) {
        this.id = Integer.valueOf(id);
        if (this.id > 0)
        {
            this.model = userService.getUserById(this.id);
        }
    }
    public int getId()
    {
        return this.id;
    }
    // GET /user
    public HttpHeaders index() {
        list = userService.getUsers();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    // GET /user/1
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    // POST /user
    public HttpHeaders create() {
        userService.createUser(model);
        return new DefaultHttpHeaders("created");
    }
    @Override
    public Object getModel()
    {
        return (list != null ? list : model);
    }
}
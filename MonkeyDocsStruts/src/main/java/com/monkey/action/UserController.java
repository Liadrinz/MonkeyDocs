package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.entity.User;
import com.monkey.service.UserService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/user", "actionName", "${id}"}))
public class UserController extends StrutsRestController<User> {
    public UserController() {
        model = new User();
    }
    @Resource(name = "userService")
    public void setSuperService(UserService userService) {
        super.service = userService;
    }
}
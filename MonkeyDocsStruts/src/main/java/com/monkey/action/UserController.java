package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.entity.Fragment;
import com.monkey.entity.User;
import com.monkey.service.UserService;
import com.monkey.util.FormField;
import com.monkey.util.Security;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.HttpHeaders;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/user", "actionName", "${id}"}))
public class UserController extends StrutsRestController<User> {
    public UserController() {
        name = "user";
        model = new User();
    }
    @Resource(name = "userService")
    public void setSuperService(UserService userService) {
        super.service = userService;
    }
    @Override
    public HttpHeaders create() {
        model.setPassword(Security.encryptPwd(model.getPassword()));
        return super.create();
    }
}
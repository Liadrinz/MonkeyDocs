package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.MetaDAO;
import com.monkey.dao.UserDAO;
import com.monkey.entity.User;
import com.monkey.service.UserService;
import com.monkey.util.Security;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.rest.HttpHeaders;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/user", "actionName", "${id}"}))
public class UserController extends StrutsRestController<User> {
    public UserController() {
        name = "user";
        model = new User();
    }
    @Resource(name = "userDAO")
    public void setSuperDAO(UserDAO dao) {
        super.dao = dao;
    }
    @Override
    public HttpHeaders create() {
        model.setPassword(Security.encryptPwd(model.getPassword()));
        return super.create();
    }

    @Override
    public HttpHeaders update() {
        if (model.getPassword() != null && !model.getPassword().equals(super.dao.findOne(model.getId()).getPassword())) {
            model.setPassword(Security.encryptPwd(model.getPassword()));
        } else {
            model.setPassword(null);
        }
        return super.update();
    }
}
package com.monkey.service;

import com.monkey.dao.UserDAO;
import com.monkey.entity.User;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService extends RestService<User> {
    @Resource(name = "userDAO")
    public void setSuperDAO(UserDAO userDAO) { super.dao = userDAO; }
}

package com.monkey.service;

import com.monkey.dao.UserDAO;
import com.monkey.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserService {
    @Resource
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.findAll();
    }

    public User getUserById(int id) {
        return userDAO.findOne(id);
    }

    public List<User> getUsersByNickname(String nickname) {
        User example = new User();
        example.setNickname(nickname);
        return userDAO.findByExample(example);
    }

    public User createUser(User user) {
        return userDAO.create(user);
    }

    public User updateUser(int id, User user) {
        return userDAO.updateOne(id, user);
    }

    public User deleteUser(int id) {
        return userDAO.deleteOne(id);
    }
}

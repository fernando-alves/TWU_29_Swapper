package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    private UserDao userMapper;

    @Autowired
    public UserService(UserDao userMapper) {
        this.userMapper = userMapper;
    }

    public User getUser(String name){
        return userMapper.getUserByName(name);
    }
}

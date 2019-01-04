package com.example.demo.service.Impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByName(String name) {
        List list = userDao.selectAll();

        return userDao.selectUserByName(name);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public User selectUserByUserNameAndPassword(User user) {
        return userDao.selectUserByUserNameAndPassword(user);
    }

    @Override
    public int insert(User record) {
        record.setId(UUID.randomUUID().toString());
        return userDao.insert(record);
    }


}

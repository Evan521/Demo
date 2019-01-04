package com.example.demo.dao;


import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    User selectUserByName(String name);

    List<User> selectAll();

    User selectUserByUserNameAndPassword(User user);
    int insert(User user);

}

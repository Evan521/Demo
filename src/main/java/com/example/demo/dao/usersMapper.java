package com.example.demo.dao;

import com.example.demo.domain.users;
import java.util.List;

public interface usersMapper {
    int insert(users record);

    List<users> selectAll();
}
package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.domain.utilModel.MessageResult;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/login")
    public MessageResult login(@RequestBody User record,HttpSession session){
        MessageResult messageResult = new MessageResult();
        User user = userService.selectUserByUserNameAndPassword(record);
        if(user!=null){
            messageResult.setStatus(true);
            messageResult.setData(user);
            session.setAttribute("username",user.getUserName());
            session.setMaxInactiveInterval(1*60);//以秒为单位
            String a = (String) session.getAttribute("username");
            System.out.println(a);
        }else{
            messageResult.setStatus(false);
        }
        return messageResult;
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public MessageResult register(@RequestBody User record){
        MessageResult messageResult = new MessageResult();
            int user = userService.insert(record);
        if(user!=0){
            messageResult.setStatus(true);
            messageResult.setData(user);
        }else{
            messageResult.setStatus(false);
        }
        return messageResult;
    }



}

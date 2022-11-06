package com.I5E2.todo.controller;

import com.I5E2.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginMember(Member member){
        return "login";
    }

}

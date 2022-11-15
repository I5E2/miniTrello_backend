package com.I5E2.todo.service;

import com.I5E2.todo.domain.SessionUserDto;
import com.I5E2.todo.domain.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService extends OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    User registerUser(User user);
    User getUserByLoginId(String userLoginId);
    User changeUserInfo(User newUser);
    SessionUserDto login(String loginId, String password);
    void logout();
}

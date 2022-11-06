package com.I5E2.todo.service;

import com.I5E2.todo.repository.UserRepository;
import com.I5E2.todo.security.auth.OAuthAttributes;
import com.I5E2.todo.domain.SessionUserDto;
import com.I5E2.todo.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;



    //소셜로그인을 통한 유저 저장
    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        log.debug("---------------userrequest-------------");
        log.debug(new ObjectMapper().registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(userRequest));
        log.debug("---------------oAuth2User-------------");
        log.debug(new ObjectMapper().registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(oAuth2User));

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName =  userRequest.getClientRegistration()
                                        .getProviderDetails()
                                        .getUserInfoEndpoint()
                                        .getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes.getAttributes(),attributes.getNameAttributeKey());
    }


    @Override
    public User registerUser(User user) {
        if(user.getJoinCode().getCodeName().equals("홈페이지가입")){
            if(userRepository.findByLoginId(user.getLoginId()).isPresent()){
                throw new IllegalArgumentException("존재하는 ID 입니다.");
            }
        }else{
            if(userRepository.findByLoginId(user.getLoginId()).isPresent()){
                return changeUserInfo(user);
            }
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByLoginId(String userLoginId) {
        return userRepository.findByLoginId(userLoginId).get();
    }

    @Override
    public User changeUserInfo(User newUser) {
        User user = userRepository.findByLoginId(newUser.getLoginId()).orElseThrow(()->
                    new IllegalArgumentException("changeUserInfo: 데이터 베이스에 변경할 객체가 없습니다."));
        user.changeInfo(newUser.getPassword(), newUser.getName(), newUser.getEmail());
        userRepository.save(user);
        return user;
    }

    @Override
    public SessionUserDto login(String loginId,String password) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(()->new BadCredentialsException("아이디나 비밀번호가 맞지 않습니다"));
        if(!user.getPassword().equals(password)){
            throw new BadCredentialsException("아이디나 비밀번호가 맞지 않습니다.");
        }
        return new SessionUserDto(user,null);
    }

    @Override
    public void logout() {

    }



}

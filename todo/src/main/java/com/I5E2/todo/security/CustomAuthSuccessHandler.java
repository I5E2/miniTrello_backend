package com.I5E2.todo.security;

import com.I5E2.todo.domain.CommonCode;
import com.I5E2.todo.domain.SessionUserDto;
import com.I5E2.todo.domain.User;
import com.I5E2.todo.repository.CommonCodeRepository;
import com.I5E2.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * 로그인 성공시 실행시킬 핸들러
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final OAuth2AuthorizedClientService clientService;
    private final CommonCodeRepository codeRepository;
    private final UserService userService;
    private final HttpSession session;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        OAuth2AuthorizedClient authorizedClient = clientService.loadAuthorizedClient(auth.getAuthorizedClientRegistrationId(), authentication.getName());
        OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();
        CommonCode googlecode = codeRepository.findBycodeName("구글가입")
                .orElseThrow(()->new IllegalArgumentException("Database에 공통코드 google이 없습니다."));
        User user = User.builder()
                .loginId(Arrays.stream(oAuth2User.getAttribute("email").toString().split("@")).findFirst().get())
                .password(null)
                .name(oAuth2User.getAttribute("name"))
                .email(oAuth2User.getAttribute("email"))
                .joinCode(googlecode)
                .joinToken(refreshToken.getTokenValue()).build();
        session.setAttribute("user",new SessionUserDto(user,oAuth2User.getAttribute("picture")));
        userService.registerUser(user);
        /*User user = new User(
                Arrays.stream(attributes.getEmail().split("@")).findFirst().get(),
                null, googlecode,refreshToken,attributes.getName(),attributes.getEmail());
        String picture = (String) oAuth2User.getAttribute("picture");
        session.setAttribute("user",new SessionUserDto(user,picture));
        userRepository.save(user);*/
        log.info("==============SuccessHandler==========");
        response.sendRedirect("/");
    }


}

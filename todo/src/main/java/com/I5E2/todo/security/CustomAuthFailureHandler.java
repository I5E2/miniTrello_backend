package com.I5E2.todo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 로그인 실패시 실행시킬 핸들러
 */
@Slf4j
@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    private final String DEFAULT_FAILURE_URL="login?error=true";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMessage = "비밀번호가 맞지 않습니다.";
        }else if(exception instanceof DisabledException){
            errorMessage = "계정이 비활성화되었습니다. 관리자에게 문의하세요";
        }else if(exception instanceof CredentialsExpiredException){
            errorMessage = "비밀번호 유효기간이 만료되었습니다. 관리자에게 문의하세요";
        }else{
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요";
        }
        request.setAttribute("errorMessage",errorMessage);

        //redirection
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request,response);

    }
}

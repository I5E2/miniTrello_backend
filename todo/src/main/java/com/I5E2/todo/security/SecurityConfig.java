package com.I5E2.todo.security;

import com.I5E2.todo.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    /*oAuth*/
    private final UserServiceImpl oAuth2UserService;
    private final CustomAuthSuccessHandler authSuccessHandler;
    private final ClientRegistrationRepository clientRegistrationRepository;


    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().disable().cors()
				// .and()
				// .authorizeRequests()
				// .antMatchers("/", "/auth/**", "/posts/read/**",
				// "/posts/search/**").permitAll()
				// anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2Login()
				.authorizationEndpoint()
				.authorizationRequestResolver(new CustomAuthorizationRequestResolver(this.clientRegistrationRepository))
				.and().userInfoEndpoint().userService(oAuth2UserService).and().successHandler(authSuccessHandler)
				.failureHandler(authenticationFailureHandler()).and().logout().logoutSuccessUrl("/")
				// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true).deleteCookies("JESSIONID");
		return http.build();
	}




    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)-> web.ignoring().antMatchers("/css/**","/js/**","/img/**");
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthFailureHandler();
    }



}

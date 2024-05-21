package com.example.weblogin;

import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.application.usecase.FindMemberUseCase;
import com.example.weblogin.filter.LoginCheckFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.*;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final AuthenticationUseCase authenticationUseCase;
    private final FindMemberUseCase findMemberUseCase;

    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter(authenticationUseCase, findMemberUseCase));
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }

}

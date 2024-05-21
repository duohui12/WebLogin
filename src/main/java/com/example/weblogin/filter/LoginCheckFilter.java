package com.example.weblogin.filter;

import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.application.usecase.FindMemberUseCase;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {

    private final AuthenticationUseCase authenticationUseCase;
    private final FindMemberUseCase findMemberUseCase;

    private static final String[] whiteList = {
            "/"
            ,"/login"
            ,"/logout"
            ,"/members/add"
            ,"/css/*"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestUri = httpServletRequest.getRequestURI();

        try{
            log.info("인증체크 필터 시작 {}", requestUri );

            if(isLoginCheckPath(requestUri)){
                log.info("인증체크 로직 실행 {}", requestUri);

                Long id = authenticationUseCase.getUserIdFromAccessToken(httpServletRequest);

                if(id == null || findMemberUseCase.findMemberById(id).isEmpty()){
                    log.info("미인증 사용자 요청 (id is null) {}", requestUri);
                    httpServletResponse.sendRedirect("/login?redirectURL=" + requestUri);
                    return;  //미인증 사용자는 더이상 진행하지 않고 여기서 끝
                }

            }

            chain.doFilter(request, response);

        }catch(Exception ex){
            throw ex;
        }finally{
            log.info("인증체크 필터 종료 {}", requestUri);
        }

    }

    private boolean isLoginCheckPath(String requestUri){
        return !PatternMatchUtils.simpleMatch(whiteList,requestUri);
    }

}

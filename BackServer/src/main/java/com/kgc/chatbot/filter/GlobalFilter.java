package com.kgc.chatbot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class GlobalFilter extends OncePerRequestFilter {

	  @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(response);
        //이렇게 한다고 해도 Byte의 길이만 설정해 두고 컨탠츠들은 복사하지 않는다 그래서 doFilter 다음에 실행시킨다.

        filterChain.doFilter(httpServletRequest, httpServletResponse);
        //후처리

        httpServletResponse.copyBodyToResponse(); //다시한번 더 바디를 채워준다.
        
       

    }
}

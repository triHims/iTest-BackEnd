package com.itest.baseapplication.Auth;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    static Set <String> skipUrls = new HashSet <>(Arrays.asList("/health",
            "/noauth/*",
            "/noauth/login",
            "/api/auth/**","/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"));
    static AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    private AuthUtil authUtil;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {


        return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    @Override
    public void doFilterInternal( HttpServletRequest servletRequest, HttpServletResponse response
        , FilterChain chain) throws ServletException, IOException {


            final String token = servletRequest.getHeader("Authorization");


            String jwtToken           = null;




            if(token !=null && (token.startsWith("Bearer")||token.startsWith("bearer"))){
                jwtToken = token.substring(7);

            }
            else {
                throw new IOException("Token does not begin with bearer");
            }

            if(jwtToken!=null && SecurityContextHolder.getContext()
                                        .getAuthentication() ==null ){

               SecurityContextHolder.getContext().setAuthentication(new JwtTokenWrapper(jwtToken));

            }
            chain.doFilter(servletRequest,response);
    }


}

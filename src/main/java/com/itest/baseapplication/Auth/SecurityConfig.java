package com.itest.baseapplication.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntry jwtAuthenticationEntry;


    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    @Autowired
    private  JwtAuthenticationProvider jwtAuthenticationProvider;

//
    @Override
    public void configure( AuthenticationManagerBuilder authBuilder ) throws Exception {
        authBuilder.authenticationProvider(jwtAuthenticationProvider);
    }


    @Override
    protected void configure( HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();

           httpSecurity.antMatcher("/**/**")
                .authorizeRequests().
                   antMatchers("noauth/**").permitAll()
                   .antMatchers( "/noauth/*",
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
                           "/**/*.js").permitAll()
                   .antMatchers("/**").authenticated()
                   .and()
                   .httpBasic()
                   .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntry);


        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.cors();

    }
}

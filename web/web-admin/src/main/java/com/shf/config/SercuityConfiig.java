package com.shf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SercuityConfiig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        使用auth对象调用在内存使用者的方法调用设置一个用户然后调用设置密码
////        密码得使用密文加密,可以调用密码加密方式
//        auth.inMemoryAuthentication().withUser("lucky").password(new BCryptPasswordEncoder().encode("123456")).roles("");
//    }

//    设置密码解密的方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //保持内联方式的打开

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();//http的请求头调用的方法内联方式使用的是同一个源头
        http.authorizeRequests().antMatchers("/static/**","/login").permitAll().anyRequest().authenticated().and();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").and();
    }
}


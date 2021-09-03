package com.example.cse_412_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfig.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Spring Security ignores request to static resources such as CSS or JS files.
        web.ignoring().antMatchers("/static/**");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .defaultSuccessUrl("/", true)
//                .loginPage("/login").permitAll()
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/")
//                .and().exceptionHandling().accessDeniedPage("/403")
//                // Configures url based authorization
//                .and()
//                .authorizeRequests()
//                // Anyone can access the urls
//                .antMatchers("/", "/resources/**",
//                        "/register").permitAll()
//                // The rest of the our application is protected.
//                .anyRequest().hasRole("USER");
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}

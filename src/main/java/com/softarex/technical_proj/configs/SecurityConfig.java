package com.softarex.technical_proj.configs;

import com.softarex.technical_proj.services.SecurityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecurityService usersService;

    public SecurityConfig(SecurityService service) {
        usersService = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usersService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                    .antMatchers("/login", "/signUp", "/login-error").not().fullyAuthenticated()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                        .failureUrl("/login-error")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/questions", true)
                .and()
                    .logout().logoutSuccessUrl("/login").permitAll();
    }
}

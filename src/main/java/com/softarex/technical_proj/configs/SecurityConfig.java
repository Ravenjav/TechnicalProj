package com.softarex.technical_proj.configs;

import com.softarex.technical_proj.services.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecurityService usersService;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(SecurityService service, PasswordEncoder passwordEncoder) {
        usersService = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usersService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                    .antMatchers("/login", "/signUp", "/login-error")
                        .not().fullyAuthenticated()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login-error")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/questions", true)
                .and()
                    .logout()
                        .logoutSuccessUrl("/login").permitAll();
    }
}

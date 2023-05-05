package Belarus.Softarex.TechnicalProj.configs;

import Belarus.Softarex.TechnicalProj.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource);
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
                        .defaultSuccessUrl("/main", true)
                .and()
                    .logout().logoutSuccessUrl("/login");
    }
}

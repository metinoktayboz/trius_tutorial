package trius.springframework.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/sign")
                .loginPage("/login")
                .successForwardUrl("/product/list")
                .defaultSuccessUrl("/product/list")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}


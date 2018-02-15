package com.github.jaytobi.ms.accounting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration of Spring Security with Spring MVC - the reactive WebFlux alternative needs a different configuration.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //config of URLs: order is important; wildcards using ANT syntax
        http.authorizeRequests()
                .antMatchers("/login").permitAll()  //allows access to everyone without authentication !
                .antMatchers("/monitor").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN") //allows only users with the ADMIN role - simple RBAC
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();  //uses HTTP Basic authentication - authorization header with user name and base64 encoded password
    }

    /**
     * For testing purpose a simple {@link InMemoryUserDetailsManager} is used with two hard-coded user, password and one role.
     * In production this can be replaced with JDBC, LDAP, ... see https://docs.spring.io/spring-security/site/docs/5.0.1.RELEASE/reference/htmlsingle/#jc-authentication-jdbc
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }
}

package ru.sbt.pprbcf.client.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.sbt.pprbcf.core.auth.remember.RememberMeServiceBase;

/**
 * Created by sbt-morozov-kv on 08.02.2017.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"ru.sbt.pprbcf.core.auth.remember", "ru.sbt.pprbcf.client.host"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private FakeUserDetailsService fakeUserDetailsService;

    @Autowired
    RememberMeServiceBase rememberMeServices;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.fakeUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(new SavedRequestAwareAuthenticationSuccessHandler()).permitAll()
                .and()
                .httpBasic()
                .and()
                .rememberMe().rememberMeServices(rememberMeServices)
                .and()
                .csrf().disable()
                .logout().logoutSuccessUrl("/greeting");
    }
}

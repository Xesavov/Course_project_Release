package com.orangeteam.NewAuc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        BCryptPasswordEncoder encoder = passwordEncoder();

//        auth.inMemoryAuthentication().withUser("Xesavov").password(passwordEncoder().encode("user")).roles("USER");
//        auth.inMemoryAuthentication().withUser("mariti8").password(passwordEncoder().encode("user")).roles("USER");
//        auth.inMemoryAuthentication().withUser("a_sey").password(passwordEncoder().encode("user")).roles("USER");
        auth.inMemoryAuthentication().withUser("moder").password(passwordEncoder().encode("moder")).roles("MODER");
        auth.inMemoryAuthentication().withUser("tetenka").password(passwordEncoder().encode("tetenka")).roles("TETENKA");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select login, password, activity from USERDESC where login=?")
                .authoritiesByUsernameQuery("select u.login, ur.roles from USERDESC u, USER_ROLE ur where u.id=ur.userdesc_id AND u.login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/lk/**").access("hasRole('ROLE_USER')")
                .antMatchers("/tetenka/**").access("hasRole('ROLE_TETENKA')")
                .antMatchers("/protected/**").access("hasRole('ROLE_MODER')")
                .antMatchers("/confidential/**").access("hasRole('ROLE_ADMIN')")
                .and().formLogin().defaultSuccessUrl("/", false);
        http.csrf().disable();
        http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/image/**").hasRole("ADMIN")
    }
}
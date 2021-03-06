package com.security.config;

import com.security.Service.CustomUserDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request-> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
                .antMatchers("/*/protected/**").hasAnyRole("USER")
                .antMatchers("/*/admin/**").hasAnyRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
    }

    @SneakyThrows
    @Autowired
    public void configure(AuthenticationManagerBuilder auth){
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//     http.authorizeRequests()
//     .antMatchers("/*/protected/**").hasAnyRole("USER")
//     .antMatchers("/*/admin/**").hasAnyRole("ADMIN")
//     .and()
//     .httpBasic().and().csrf().disable();
//    }
//
//    @SneakyThrows
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth){
//        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
//
//    }

//    @SneakyThrows
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth){
//        auth.inMemoryAuthentication()
//                .withUser("william").password("devdojo").roles("USER")
//                .and()
//                .withUser("admin").password("devdojo").roles("USER","ADMIN");
//
//    }
}

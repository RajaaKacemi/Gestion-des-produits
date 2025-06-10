package com.example.bdccensetspringenset.security;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(BeanFactory beanFactory){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build()

        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(fl->fl.loginPage("/login").permitAll())
//                .csrf(csrf->csrf.disable())
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests((ar-> ar.requestMatchers("/user/**").hasRole("USER")))
                .authorizeHttpRequests((ar-> ar.requestMatchers("/admin/**").hasRole("ADMIN")))
                .authorizeHttpRequests((ar-> ar.requestMatchers("/public/**").permitAll()))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .exceptionHandling(eh-> eh.accessDeniedPage("/notAuthorized"))
                .build();
    }


}

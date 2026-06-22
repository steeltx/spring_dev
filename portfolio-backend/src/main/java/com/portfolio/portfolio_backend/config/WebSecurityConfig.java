package com.portfolio.portfolio_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.portfolio.portfolio_backend.service.IUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final IUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> 
            auth
            .requestMatchers("/education","/experience","/skills","/projects","/personal-info").authenticated()
            .requestMatchers("/education/new","/education/save","/education/edit/**","/education/delete/**").authenticated()
            .requestMatchers("/experience/new", "/experience/save", "/experience/edit/**","/experience/delete/**").authenticated()
            .requestMatchers("/skills/new", "/skills/save", "/skills/edit/**","/skills/delete/**").authenticated()
            .requestMatchers("/personal-info/create", "/personal-info/edit/**","/personal-info/save").authenticated()
            // busqueda de personal info por id igual debe de estar protegida
            .requestMatchers("/education/personal/**", "/experience/personal/**","/skills/personal/**").authenticated()
            .requestMatchers("/projects/new-project", "/projects/save").authenticated()
            .anyRequest().permitAll())
            .formLogin(form -> 
                form.loginPage("/login").permitAll())
            .logout(logout -> logout.logoutUrl("/logout")
                .logoutRequestMatcher(request -> "GET".equalsIgnoreCase(request.getMethod()) && "/logout".equals(request.getRequestURI()))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll());
        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
    //     UserDetails user = User
    //         .withUsername("admin")
    //         .password(passwordEncoder.encode("1234"))
    //         .roles("ADMIN")
    //         .build();
    //     return new InMemoryUserDetailsManager(user);
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

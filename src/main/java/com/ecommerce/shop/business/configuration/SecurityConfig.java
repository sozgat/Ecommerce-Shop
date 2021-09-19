package com.ecommerce.shop.business.configuration;

import com.ecommerce.shop.business.context.filter.JwtTokenFilter;
import com.ecommerce.shop.business.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserService userService, JwtTokenFilter jwtTokenFilter) {
        this.userService = userService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers()
                .frameOptions().disable()
                .and()
                // // Enable CORS and disable CSRF
                .csrf().disable()
                .cors()
                .and()
                // Set session management to stateless
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Set unauthorized requests exception handler
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> response.sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                ex.getMessage()
                        )
                )
                .and()
                // Set permissions on endpoints
                .authorizeRequests()
                // public endpoints
                .antMatchers("/api/auth/*").permitAll()
                .antMatchers("/api/public/**").permitAll()
                // .antMatchers(MappingConstant.DEFAULT_API_PATH + "/**").permitAll()
                // .antMatchers(MappingConstant.DEFAULT_TRANSACTION_PATH + "/**").permitAll()
                // private endpoints
                // .antMatchers("/api/admin/user/**").hasRole(Role.USER_ADMIN)
                .anyRequest().authenticated()
                .and()
                // logout configuration
                .logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                // .accessDeniedPage("/access-denied")
                // .authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"))
                .and()
                .addFilterBefore(
                        jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

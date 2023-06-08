package com.tokenUdemy.Udmey.config;

import com.tokenUdemy.Udmey.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");
//        http.securityContext().requireExplicitSave(false)
//                        .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()
//                .csrf().ignoringRequestMatchers("/contact","/register").
        .csrf((csrf)-> csrf.csrfTokenRequestHandler(requestAttributeHandler).ignoringRequestMatchers("/contact","/register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new RequestValidationBeforeFilter(),BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthoritiesLoggingAfterFilter(),BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(),BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(),BasicAuthenticationFilter.class)
//                .and().ignoringRequestMatchers
                .authorizeHttpRequests()
//                .requestMatchers("/myAccount").hasRole("USER")
//                .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
//                .requestMatchers("/myLoans").hasRole("USER")
//                .requestMatchers("/myCards").hasRole("USER")
//                .requestMatchers("/user").authenticated()
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/register","/user").permitAll()
                .and().formLogin()
                .and().httpBasic();

        return (SecurityFilterChain) http.build();
    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
////        Approach 1 where we use withDefaultPasswordEncoder method while creating user details
//     /*   UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//      */
//        // Approach 2 where we use NoOpPasswordEncoder
//        UserDetails admin = User.withUsername("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(admin,user);
//
//    }
// NoOpPasswordEncoder is not recommended for production
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

}

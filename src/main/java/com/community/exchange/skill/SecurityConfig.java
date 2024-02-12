/*
 * package com.community.exchange.skill; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .authorizeRequests() .antMatchers("/skillapp/**","/user/**").permitAll()
 * .anyRequest().authenticated() // Require authentication for all other .and()
 * .formLogin() .loginPage("/skillapp") // Specify index.jsp as .permitAll()
 * .and() .logout() .permitAll() .logoutSuccessUrl("/logout"); } }
 */
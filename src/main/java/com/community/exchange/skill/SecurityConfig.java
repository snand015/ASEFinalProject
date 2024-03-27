package com.community.exchange.skill;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	    }


    @Override
    protected void configure(HttpSecurity https) throws Exception {
        https
            
            .authorizeRequests()
                .antMatchers("/skillapp/**","/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/skillapp")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/skillapp?logout")
                .permitAll()
                .and()
            .csrf().disable();
    }
}
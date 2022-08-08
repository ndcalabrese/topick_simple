package com.ndcalabrese.topick_simple.config;

import com.ndcalabrese.topick_simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/",
//                        "/signup**",
//                        "/register**",
//                        "/api/posts/by-subtopick/**",
//                        "/api/posts/view-post/**",
//                        "/api/subtopicks/list",
//                        "/api/user/profile/**",
//                        "/js/**",
//                        "/css/**",
//                        "/img/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/posts/by-subtopick/**").permitAll()
                .antMatchers("/api/posts/view-post/**").permitAll()
                .antMatchers("/api/subtopicks/list").permitAll()
                .antMatchers("/api/user/profile/**").permitAll()
                .antMatchers("/api/user/profile/me").authenticated()
                .antMatchers("/api/posts/create").authenticated()
                .antMatchers("/api/subtopicks/create").authenticated()
                .antMatchers("/api/posts/view-post/**/comments/create").authenticated()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/?LoginSuccess")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?LogoutSuccess")
                .permitAll()
                //new fix to 403 on js script for upvote/downvote
                .and().csrf().disable().cors();

    }

}

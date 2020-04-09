package com.example.Library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Авторизация
                    .antMatchers("/", "registration").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/loginpage")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("a")
//                        .password("a")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.jdbcAuthentication().dataSource(dataSource);
       auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance());
       auth.jdbcAuthentication().usersByUsernameQuery("select username , password ,active from usr where username=?"); // запрос в базу данных
       auth.jdbcAuthentication().authoritiesByUsernameQuery("select u.username , ur.roles from usr u inner join user_role ur on u.id = ur.usser_id where u.username=?"); //запрос из таблицы usr присоедененной к ней таблицы user_role соеденные через поля
    }
}

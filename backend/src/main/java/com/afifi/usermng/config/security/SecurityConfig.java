package com.afifi.usermng.config.security;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;

//@EnableWebSecurity
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.addFilterBefore(new CustomFilter(), ChannelProcessingFilter.class);
//        httpSecurity.authorizeRequests()
//                .antMatchers("/")
//                .permitAll()
//                .anyRequest()
//                //.permitAll()
//                .fullyAuthenticated()
//                .and().httpBasic().and().csrf().disable();
//    }
//
//}

package com.example.oauth2google.config

import com.example.oauth2google.service.oauth.CustomOidUserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService


@Configuration
@EnableWebSecurity
class SecurityConfig (
        private val oidUserService : CustomOidUserService
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {

        http
                .authorizeRequests()
                .antMatchers("/app/byMail").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/myauthorizedredirecturi/*")
                .and()
                .userInfoEndpoint()
                .oidcUserService(oidUserService)
    }
}
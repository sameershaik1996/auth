package com.redshift.auth2.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:application.properties")
public  class AuthConfig {
    @Value("${app.jwtSecret}")
    public  String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    public  String jwtExpTime;


    /*public AuthConfig( @Value("${app.jwtSecret}")String jwtSecret, @Value("$app.jwtExpirationInMs")String jwtExpTime) {
        this.jwtSecret = jwtSecret;
        this.jwtExpTime = jwtExpTime;
    }*/

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(@Value("${app.jwtSecret}")String jwtSecret) {
        System.out.println(jwtSecret);
        this.jwtSecret = jwtSecret;
    }

    public String getJwtExpTime() {
        return jwtExpTime;
    }

    public void setJwtExpTime(@Value("${app.jwtExpirationInMs}")String jwtExpTime) {
        this.jwtExpTime = jwtExpTime;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

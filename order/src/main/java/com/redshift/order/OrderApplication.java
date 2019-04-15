package com.redshift.order;

import com.redshift.auth2.Security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns(new String[]{"/api/*"});
		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}

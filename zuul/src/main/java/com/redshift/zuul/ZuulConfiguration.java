package com.redshift.zuul;

import com.redshift.zuul.ZuulFilters.ErrorFilter;
import com.redshift.zuul.ZuulFilters.PostFilter;
import com.redshift.zuul.ZuulFilters.PreFilter;
import com.redshift.zuul.ZuulFilters.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {


    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();


    }
}

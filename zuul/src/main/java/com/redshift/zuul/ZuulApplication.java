package com.redshift.zuul;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.redshift.zuul.ZuulFilters.ErrorFilter;
import com.redshift.zuul.ZuulFilters.PostFilter;
import com.redshift.zuul.ZuulFilters.PreFilter;
import com.redshift.zuul.ZuulFilters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableCaching
public class ZuulApplication {
	@Bean

	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Config hazelCastConfig(){
		Config config = new Config();
		config.setInstanceName("hazelcast-instance")
				.addMapConfig(
						new MapConfig()
								.setName("configuration")
								.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
								.setEvictionPolicy(EvictionPolicy.LRU)
								.setTimeToLiveSeconds(-1));
		return config;
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}



}

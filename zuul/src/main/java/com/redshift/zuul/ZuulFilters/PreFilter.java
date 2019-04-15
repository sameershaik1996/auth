package com.redshift.zuul.ZuulFilters;

import javax.servlet.http.HttpServletRequest;

import com.hazelcast.core.HazelcastInstance;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

public class PreFilter extends ZuulFilter {

    @Autowired
    private HazelcastInstance instance;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Map<String,String> authMap=instance.getMap("auth");

        System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        String authHeader="";
        authHeader= request.getHeader("Authorization");


        if(!request.getRequestURI().contains("auth-service")){
            System.out.println("inside:"+request.getContextPath());
            authHeader= request.getHeader("Authorization");
            if (authHeader == null || authHeader.isEmpty() ) {
                ctx.setResponseStatusCode(401);
                ctx.setSendZuulResponse(false);
            }
            try {
                HttpHeaders headers = new HttpHeaders();
                System.out.println("passed opaque token"+authMap.get(request.getHeader("Authorization")));
                if(!authMap.get(request.getHeader("Authorization")).isEmpty()) {
                    String token=authMap.get(request.getHeader("Authorization"));
                    System.out.println("passed opaque token:"+token);
                    //headers.add("Authorization", authMap.get(request.getHeader("Authorization")));
                    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
                   // ResponseEntity<String> response = restTemplate.exchange("http://localhost:8091/auth/authorize", HttpMethod.GET, entity, String.class);
                    //System.out.println(response.getStatusCode());
                    //if (response.getStatusCode().equals(HttpStatus.OK)) {
                    token="Bearer "+token;
                        ctx.addZuulRequestHeader("Authorization", token);
                    //}
                }
                else{
                    ctx.setResponseStatusCode(401);
                    ctx.setSendZuulResponse(false);
                }
            }catch (Exception e){
                e.printStackTrace();
                ctx.setResponseStatusCode(500);
                ctx.setSendZuulResponse(false);
            }
        }
        else{

        }
       /*  HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",request.getHeader("Authorization"));
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/auth-service/authorize", HttpMethod.GET, entity,String.class);
        System.out.println(response.getStatusCode());*/

        return null;
    }
}

package com.redshift.zuul.ZuulFilters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.hazelcast.core.HazelcastInstance;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.redshift.zuul.Model.LoginResponse;
import com.redshift.zuul.Model.Token;
import com.redshift.zuul.utility.Converter;
import com.sun.xml.internal.ws.client.ResponseContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

public class PostFilter extends ZuulFilter {

    @Autowired
    private HazelcastInstance instance;

    @Override
    public String filterType() {
        return "post";
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
        if(request.getRequestURI().contains("signin")) {
            try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
                final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));

                //ObjectMapper mapper = new ObjectMapper();
                  //ctx.setResponseBody(responseData);
                //Token readValue = mapper.readValue(responseData, Token.class);
                //System.out.println("readValue = " + readValue);
                //System.out.println("readValue = " + responseData);
                Map<String,String> authMap=instance.getMap("auth");

                String s=seesionIdGen();
                authMap.put(s,responseData);
               //LoginResponse l= ctx.getResponseBody();
                //System.out.println(authMap.get(ctx.getResponseBody()));
                ctx.setResponseBody(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }


    public String seesionIdGen(){
        int n=25;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}

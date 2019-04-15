package com.redshift.order.Controller;


import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Orders {

    @RequestMapping("/orders")
    public String sampleController(@RequestHeader(value = "Authorization")String authHead ){
        System.out.println("header:"+authHead);
        return "Sameer";

    }

    @RequestMapping("/api/orders")
    public String sampleController1(@RequestHeader(value = "Authorization")String authHead , HttpServletRequest req){
        Claims c = (Claims)req.getAttribute("claims");
        System.out.println("asd" + c.get("username"));

        return "Sameer";

    }
}
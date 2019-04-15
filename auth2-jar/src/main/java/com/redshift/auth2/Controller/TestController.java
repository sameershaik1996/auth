package com.redshift.auth2.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @RequestMapping("/api/test")
    public void Test(HttpServletRequest req){
        Claims c=(Claims) req.getAttribute("claims");
        System.out.println("asd"+c.get("username"));
    }
}

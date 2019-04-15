package com.redshift.auth2.controller;

import com.redshift.auth2.security.CurrentUser;
import com.redshift.auth2.security.UserPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
@RequestMapping("")

public String Test(@CurrentUser UserPrincipal userPrincipal){
    return userPrincipal.getUsername()+" "+userPrincipal.getEmail();



}
}

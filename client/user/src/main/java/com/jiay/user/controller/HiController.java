package com.jiay.user.controller;

import com.jiay.common.annotation.AuthToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hi")
public class HiController {

    @AuthToken
    @RequestMapping("index")
    public String index(){
        return "hi";
    }
}

package com.jiay.user.controller;

import com.jiay.common.controller.BaseController;
import com.jiay.common.result.SingleResult;
import com.jiay.user.model.request.LoginRequest;
import com.jiay.user.model.request.RegisterRequest;
import com.jiay.user.model.response.TokenResponse;
import com.jiay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户控制器
 * @author jiay
 * @date 2020-03-17
 */
@RequestMapping("user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public SingleResult<TokenResponse> login(@Valid @RequestBody LoginRequest request, BindingResult result){
        validate(result);
        return userService.login(request);
    }

    @RequestMapping("register")
    public SingleResult<TokenResponse> register(@Valid @RequestBody RegisterRequest request,BindingResult result){
        validate(result);
        return userService.register(request);
    }


}
